#PickUp app DAO

import psycopg2
import md5

class UserAlreadyExistsException(Exception):
    def __init__(self, err):
        self.err = err
    def __str__(self):
        return 'Exception: ' + self.err
        
class NoUserExistsException(Exception):
    def __init__(self, err):
        self.err = err
    def __str__(self):
        return 'Exception: ' + self.err
        
class BadArgumentsException(Exception):
    """Exception for entering bad arguments"""
    def __init__(self, err):
        self.err = err
    def __str__(self):
        return 'Exception: ' + self.err

class PickUpDao:
    def __init__(self, dbname='pickup', pgusername='postgres', pgpasswd='psql4me'):
        self.dbname = dbname
        self.pgusername = pgusername
        self.pgpasswd = pgpasswd
        #when connect to server
        #self.dbhost = 'wherewolf.csjz2fsgpjd7.us-west-2.rds.amazonaws.com'
        print ('connection to database {}, user: {}, password: {}'.format(dbname, pgusername, pgpasswd))

    def get_db(self):
        return psycopg2.connect(database=self.dbname,user=self.pgusername,password=self.pgpasswd)
        #when connect to server
        #return psycopg2.connect(host=self.dbhost, database=self.dbname,user=self.pgusername,password=self.pgpasswd)

### works :)
## do we want user to enter location upon creating account? (add location to insert if so)
    ## need lat lng or change to can be null in schema
    def create_user(self, username, password, firstname, lastname):
        """ registers a new user in the system """
        conn = self.get_db()
        with conn:
            c = conn.cursor()
            c.execute('SELECT COUNT(*) from appuser WHERE username=%s',(username,))
            n = int(c.fetchone()[0])
            # print 'num of rfdickersons is ' + str(n)
            if n == 0:
                hashedpass = md5.new(password).hexdigest()
                c.execute('INSERT INTO appuser (username, password, firstname, lastname) VALUES (%s,%s,%s,%s)', 
                          (username, hashedpass, firstname, lastname))
                conn.commit()
            else:
                raise UserAlreadyExistsException('{} user already exists'.format((username)) )

### works :)
    def check_password(self, username, password):
        """ return true if password checks out """
        conn = self.get_db()
        with conn:
            c = conn.cursor()
            sql = ('select password from appuser where username=%s')
            c.execute(sql,(username,))
            hashedpass = md5.new(password).hexdigest()
            u = c.fetchone()
            if u == None:
                raise NoUserExistsException(username)
            # print 'database contains {}, entered password was {}'.format(u[0],hashedpass)
            return u[0] == hashedpass

### works :)
    def set_location(self, username, lat, lng):
        conn = self.get_db()
        with conn:
            cur = conn.cursor()
            sql = ('update appuser set lat=%s, lng=%s '
                   'where username=%s')
            cur.execute(sql, (lat, lng, username))
            conn.commit()

## do we need this?
    def get_user_location(self, username):
        conn = self.get_db()
        result = {}
        with conn:
            c = conn.cursor()
            sql = ('select username, lat, lng from appuser where username=%s')
            c.execute(sql, (username,))
            row = c.fetchone()
            result["username"] = row[0]
            result["lat"] = row[1]
            result["lng"] = row[2]
        print result
        return result
        
# No errors but just returns []
    def get_all_games_nearby(self, username, radius): 
        ''' returns all games near a user '''
        conn = self.get_db()
        result = []
        with conn:
            c = conn.cursor()
            sql_location = ('select lat, lng from appuser where username=%s')
            c.execute(sql_location, (username,))
            location = c.fetchone()

            if location == None:
                return result

            # using the radius for lookups now
            sql = ('select name, sport_type, date, time, game.location, '
                   'earth_distance( ll_to_earth(game.lat, game.lng), '
                   'll_to_earth(%s,%s) ), max_players '
                   'from game, appuser where '
                   'earth_box(ll_to_earth(%s,%s),%s) '
                   '@> ll_to_earth(appuser.lat, appuser.lng)')


            c.execute(sql, (location[0], location[1], 
                            location[0], location[1], 
                            radius))
            for row in c.fetchall():
                d = {}
                d["name"] = row[0]
                d["sport_type"] = row[1]
                d["date"] = row[2]
                d["time"] = row[3]
                d["location"] = row[4]
                d["distance"] = row[5]
                d["max_players"] =row[6]
                #d["distance"] = row[1]
                #d["is_werewolf"] = row[2]
                result.append(d)
            print result
            return result 

if __name__ == "__main__":
    dao = PickUpDao('pickup','postgres','psql4me')

    #dao.create_user('spider', 'pw', 'alyssa', 'gloyd')
    #dao.create_user('walid', 'pw', 'walid', 'lastname')
    #dao.create_user('kevin', 'pw', 'kevin', 'lastname')
    #dao.create_user('megan', 'pw', 'megan', 'lastname')
    '''
    username = 'spider'
    correct_pass = 'pw'
    incorrect_pass = 'scaley'
    print 'Logging in {} with {}'.format(username, correct_pass)
    print 'Result: {} '.format( dao.check_password(username, correct_pass ))
    
    print 'Logging in {} with {}'.format(username, incorrect_pass)
    print 'Result: {} '.format( dao.check_password(username, incorrect_pass ))
    '''
    #dao.set_location('spider', 1, 1)
    #dao.get_user_location('spider')
    dao.get_all_games_nearby('spider', 3)
