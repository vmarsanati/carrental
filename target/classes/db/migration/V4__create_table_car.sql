CREATE TABLE `car` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `kilometerage` bigint NULL,
  `color` VARCHAR(120) NULL,
  `plate` VARCHAR(120) NULL,
  `car_variant_id` bigint NULL,
  PRIMARY KEY (`id`),
  INDEX `car-car_variant_id_idx` (`car_variant_id` ASC) VISIBLE,
  CONSTRAINT `car-car_variant_id`
    FOREIGN KEY (`car_variant_id`)
    REFERENCES `car_variant` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
