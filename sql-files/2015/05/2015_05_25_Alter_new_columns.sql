USE phone_dictionary;
ALTER TABLE client DROP COLUMN addressId;
ALTER TABLE address ADD COLUMN clientId int(11) DEFAULT NULL;
#koropatvaua@gmail.com
