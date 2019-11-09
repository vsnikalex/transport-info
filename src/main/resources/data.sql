INSERT INTO transport_info.depot
(id, location, type)
VALUES
(1, '{"point":{"lat":48.7525249,"lng":18.1450552},"name":"Sklad","country":"Slovakia","city":"District of Bánovce nad Bebravou","state":"Region of Trenčín","postcode":"91324","extent":[],"osm_id":2663395264,"osm_type":"N","osm_key":"place","osm_value":"locality"}', 'CORP'),
(3, '{"point":{"lat":47.6866619,"lng":13.0984814},"name":"Lagerhaus","country":"Austria","city":"Hallein","state":"Salzburg","street":"Franz-Dückher-Weg","postcode":"5400","extent":[],"osm_id":1059124493,"osm_type":"N","osm_key":"shop","osm_value":"doityourself"}', 'CORP'),
(4, '{"point":{"lat":46.0944323,"lng":11.1157568},"name":"OVS industry","country":"Italy","city":"Trento","state":"Trentino-Alto Adige/Südtirol","street":"Via Giovanni Battista Trener","postcode":"38121","extent":[],"osm_id":1462173437,"osm_type":"N","osm_key":"shop","osm_value":"clothes"}', 'CLIENT'),
(5, '{"point":{"lat":46.0688369,"lng":14.4899731},"name":"Robin Food Cafè","country":"Slovenia","city":"Ljubljana","street":"Trg Prekomorskih brigad","postcode":"1001","extent":[],"osm_id":5779437853,"osm_type":"N","osm_key":"amenity","osm_value":"cafe"}', 'CLIENT'),
(6, '{"point":{"lat":47.4503132,"lng":18.9650269},"name":"Duna House","country":"Hungary","city":"Budaörs","state":"Central Hungary","street":"Kinizsi utca","postcode":"2040","extent":[],"osm_id":5383384124,"osm_type":"N","osm_key":"office","osm_value":"estate_agent"}', 'CLIENT'),
(7, '{"point":{"lat":49.01333,"lng":12.05184},"name":"Dr. Hien","country":"Germany","city":"Regensburg","state":"Bavaria","street":"Franz-von-Taxis-Ring","postcode":"93049","extent":[],"osm_id":1653823230,"osm_type":"N","osm_key":"amenity","osm_value":"dentist"}', 'CLIENT');

INSERT INTO
transport_info.truck
(id, capacity, location, plate, status)
VALUES
(1, 1000, '{"point":{"lat":48.7625,"lng":18.1061},"name":"Sklad","country":"Slovakia","city":"District of Bánovce nad Bebravou","state":"Region of Trenčín","postcode":"91324","extent":[],"osm_id":2663395264,"osm_type":"N","osm_key":"place","osm_value":"locality"}', 'SL08923', 'OK'),
(5, 1000, '{"point":{"lat":49.7280077,"lng":13.3438826},"name":"Kosmas","country":"Czech Republic","city":"Plzeň","state":"Southwest","street":"Podnikatelská","postcode":"31800","extent":[],"osm_id":5294055985,"osm_type":"N","osm_key":"shop","osm_value":"books"}', 'CZ12345', 'OK'),
(9, 2000, '{"point":{"lat":47.7983318,"lng":13.047477},"name":"Residenzplatz","country":"Austria","city":"Salzburg","state":"Salzburg","street":"Residenzplatz","postcode":"5020","extent":[],"osm_id":5808348925,"osm_type":"N","osm_key":"place","osm_value":"house"}', 'AU12345', 'OK'),
(10, 1000, '{"point":{"lat":47.7162135,"lng":13.1801681},"name":"Gasthof Krispler Wirt","country":"Austria","city":"Krispl","state":"Salzburg","postcode":"5425","extent":[],"osm_id":114275682,"osm_type":"N","osm_key":"amenity","osm_value":"restaurant"}', 'AU23456', 'OK'),
(11, 1200, '{"point":{"lat":47.7983318,"lng":13.047477},"name":"Residenzplatz","country":"Austria","city":"Salzburg","state":"Salzburg","street":"Residenzplatz","postcode":"5020","extent":[],"osm_id":5808348925,"osm_type":"N","osm_key":"place","osm_value":"house"}', 'AU09876', 'DEFECTIVE'),
(12, 1200, '{"point":{"lat":47.7162135,"lng":13.1801681},"name":"Gasthof Krispler Wirt","country":"Austria","city":"Krispl","state":"Salzburg","postcode":"5425","extent":[],"osm_id":114275682,"osm_type":"N","osm_key":"amenity","osm_value":"restaurant"}', 'AU076543', 'OK'),
(14, 1000, '{"point":{"lat":48.750986,"lng":17.828829},"name":"Kpt. Nálepku","country":"Slovakia","city":"Nové Mesto nad Váhom","state":"Region of Trenčín","street":"Kpt. Nálepku","postcode":"915 01","extent":[],"osm_id":2989631357,"osm_type":"N","osm_key":"place","osm_value":"house"}', 'SL12345', 'OK'),
(15, 1000, '{"point":{"lat":48.750986,"lng":17.828829},"name":"Kpt. Nálepku","country":"Slovakia","city":"Nové Mesto nad Váhom","state":"Region of Trenčín","street":"Kpt. Nálepku","postcode":"915 01","extent":[],"osm_id":2989631357,"osm_type":"N","osm_key":"place","osm_value":"house"}', 'SL03456', 'DEFECTIVE'),
(16, 2000, '{"point":{"lat":48.750986,"lng":17.828829},"name":"Kpt. Nálepku","country":"Slovakia","city":"Nové Mesto nad Váhom","state":"Region of Trenčín","street":"Kpt. Nálepku","postcode":"915 01","extent":[],"osm_id":2989631357,"osm_type":"N","osm_key":"place","osm_value":"house"}', 'SL56789', 'OK');

INSERT INTO transport_info.driver (id, firstName, lastName, location, delivery_id)
VALUES
(1, 'Test', 'Testovich', '{"point":{"lat":48.7525249,"lng":18.1450552},"name":"Sklad","country":"Slovakia","city":"District of Bánovce nad Bebravou","state":"Region of Trenčín","postcode":"91324","extent":[],"osm_id":2663395264,"osm_type":"N","osm_key":"place","osm_value":"locality"}', null),
(2, 'Johan', 'Bregovich', '{"point":{"lat":49.7280077,"lng":13.3438826},"name":"Kosmas","country":"Czech Republic","city":"Plzeň","state":"Southwest","street":"Podnikatelská","postcode":"31800","extent":[],"osm_id":5294055985,"osm_type":"N","osm_key":"shop","osm_value":"books"}', null),
(3, 'Robert', 'Miller', '{"point":{"lat":49.7280077,"lng":13.3438826},"name":"Kosmas","country":"Czech Republic","city":"Plzeň","state":"Southwest","street":"Podnikatelská","postcode":"31800","extent":[],"osm_id":5294055985,"osm_type":"N","osm_key":"shop","osm_value":"books"}', null),
(4, 'Richard', 'Davis', '{"point":{"lat":47.7983318,"lng":13.047477},"name":"Residenzplatz","country":"Austria","city":"Salzburg","state":"Salzburg","street":"Residenzplatz","postcode":"5020","extent":[],"osm_id":5808348925,"osm_type":"N","osm_key":"place","osm_value":"house"}', null),
(5, 'James', 'Wilson', '{"point":{"lat":47.7983318,"lng":13.047477},"name":"Residenzplatz","country":"Austria","city":"Salzburg","state":"Salzburg","street":"Residenzplatz","postcode":"5020","extent":[],"osm_id":5808348925,"osm_type":"N","osm_key":"place","osm_value":"house"}', null),
(6, 'Ivan', 'Ivanov', '{"point":{"lat":47.7162135,"lng":13.1801681},"name":"Gasthof Krispler Wirt","country":"Austria","city":"Krispl","state":"Salzburg","postcode":"5425","extent":[],"osm_id":114275682,"osm_type":"N","osm_key":"amenity","osm_value":"restaurant"}', null),
(7, 'Mikhail', 'Stepanov', '{"point":{"lat":47.7162135,"lng":13.1801681},"name":"Gasthof Krispler Wirt","country":"Austria","city":"Krispl","state":"Salzburg","postcode":"5425","extent":[],"osm_id":114275682,"osm_type":"N","osm_key":"amenity","osm_value":"restaurant"}', null),
(8, 'Oleg', 'Zverev', '{"point":{"lat":47.7162135,"lng":13.1801681},"name":"Gasthof Krispler Wirt","country":"Austria","city":"Krispl","state":"Salzburg","postcode":"5425","extent":[],"osm_id":114275682,"osm_type":"N","osm_key":"amenity","osm_value":"restaurant"}', null),
(9, 'Urii', 'Zaycev', '{"point":{"lat":48.750986,"lng":17.828829},"name":"Kpt. Nálepku","country":"Slovakia","city":"Nové Mesto nad Váhom","state":"Region of Trenčín","street":"Kpt. Nálepku","postcode":"915 01","extent":[],"osm_id":2989631357,"osm_type":"N","osm_key":"place","osm_value":"house"}', null),
(10, 'Igor', 'Lesnoy', '{"point":{"lat":48.750986,"lng":17.828829},"name":"Kpt. Nálepku","country":"Slovakia","city":"Nové Mesto nad Váhom","state":"Region of Trenčín","street":"Kpt. Nálepku","postcode":"915 01","extent":[],"osm_id":2989631357,"osm_type":"N","osm_key":"place","osm_value":"house"}', null);

INSERT INTO transport_info.cargo
(id, description, status, weight, delivery_id, endDepot_id, startDepot_id)
VALUES
(1, 'IKEA Sofa', 'PREPARED', 200, null, 4, 3),
(2, 'IKEA Table', 'PREPARED', 100, null, 5, 3),
(3, 'Tesla Model X', 'PREPARED', 2300, null, 6, 3),
(4, 'BMW Engine', 'PREPARED', 175, null, 7, 3),
(5, 'Spare Parts', 'PREPARED', 400, null, 4, 1),
(6, 'Super Computer', 'PREPARED', 900, null, 5, 1);
