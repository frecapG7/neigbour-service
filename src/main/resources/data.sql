
-- Insert countries
INSERT INTO COUNTRY (ID, NAME_FR, NAME_EN) VALUES('1','CANADA','CANADA');
INSERT INTO COUNTRY (ID, NAME_FR, NAME_EN) VALUES('2','FRANCE','FRANCE');
-- Insert Cities
INSERT INTO CITY (ID, NAME_FR, NAME_EN, COUNTRY_ID) VALUES('1', 'MONTREAL', 'MONTREAL', (SELECT id FROM COUNTRY WHERE ID = '123456789'));
INSERT INTO CITY (ID, NAME_FR, NAME_EN, COUNTRY_ID) VALUES('2', 'TORONTO', 'TORONTO', (SELECT id FROM COUNTRY WHERE ID = '123456789'));
-- Insert District
INSERT INTO DISTRICT (ID, NAME_FR, NAME_EN, DESCRIPTION_FR, DESCRIPTION_EN, CITY_ID) VALUES('1','VERDUN','VERDUN','blabka','fewf', (SELECT id FROM CITY WHERE ID = '1'));

INSERT INTO POINT_OF_INTEREST (ID, NAME, ADDRESS, URI, PHONE, CATEGORY, DISTRICT_ID) VALUES('1', 'Chez Boss & Fils', '3610 Rue Wellington, Verdun', '(514)805-3457' ,'chezbossetfils.ca' , 'R', (SELECT id FROM DISTRICT WHERE ID = '1') );
INSERT INTO POINT_OF_INTEREST (ID, NAME, ADDRESS, URI, PHONE, CATEGORY, DISTRICT_ID) VALUES('2', 'Restaurant Wellington', '3629 Rue Wellington, Verdin', '(514)419-1646', 'restaurantwellington.ca' , 'R', (SELECT id FROM DISTRICT WHERE ID = '1') );
INSERT INTO POINT_OF_INTEREST (ID, NAME, ADDRESS, URI, PHONE, CATEGORY, DISTRICT_ID) VALUES('3', 'Les Street Monkeys', '3625 Rue Wellington', '(514)768-1818', 'streetmonkeys.ca' , 'R', (SELECT id FROM DISTRICT WHERE ID = '1') );
INSERT INTO POINT_OF_INTEREST (ID, NAME, ADDRESS, URI, PHONE, CATEGORY, DISTRICT_ID) VALUES('4', 'Rita', '3681 Rue Wellington', '(514)419-1942', '' , 'R', (SELECT id FROM DISTRICT WHERE ID = '1') );
INSERT INTO POINT_OF_INTEREST (ID, NAME, ADDRESS, URI, PHONE, CATEGORY, DISTRICT_ID) VALUES('5', 'Rita', '3681 Rue Wellington', '(514)419-1942', '' , 'R', (SELECT id FROM DISTRICT WHERE ID = '1') );