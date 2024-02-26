CREATE TABLE `car_variant` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `descricao` VARCHAR(120) NULL,
  `brand_id` bigint NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `descricao_UNIQUE` (`descricao` ASC) VISIBLE,
  INDEX `car_variant-brand_id_idx` (`brand_id` ASC) VISIBLE,
  CONSTRAINT `car_variant-brand_id`
    FOREIGN KEY (`brand_id`)
    REFERENCES `brand` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
