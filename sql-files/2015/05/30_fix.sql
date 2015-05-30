ALTER TABLE `phone_dictionary`.`client` DROP COLUMN `addressId`; 
ALTER TABLE `phone_dictionary`.`address` ADD COLUMN `clientID` INT(11) NULL AFTER `city`; 

