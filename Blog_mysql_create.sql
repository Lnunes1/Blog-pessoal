CREATE TABLE `tb_temas` (
	`id` bigint NOT NULL AUTO_INCREMENT,
	`descriçao` varchar(255) NOT NULL AUTO_INCREMENT,
	PRIMARY KEY (`id`)
);

CREATE TABLE `tb_postagens` (
	`id` bigint NOT NULL AUTO_INCREMENT,
	`titulo` varchar(100) NOT NULL AUTO_INCREMENT,
	`texto` varchar(1000) NOT NULL AUTO_INCREMENT,
	`data` DATE NOT NULL AUTO_INCREMENT,
	`tema_id` DATE NOT NULL AUTO_INCREMENT,
	`usuario_id` DATE NOT NULL AUTO_INCREMENT,
	PRIMARY KEY (`id`)
);

CREATE TABLE `tb_usuarios` (
	`id` bigint NOT NULL,
	`nome` varchar(255) NOT NULL,
	`usuario` varchar(255) NOT NULL,
	`senha` varchar(255) NOT NULL,
	`foto` varchar(255) NOT NULL,
	PRIMARY KEY (`id`)
);

ALTER TABLE `tb_postagens` ADD CONSTRAINT `tb_postagens_fk0` FOREIGN KEY (`tema_id`) REFERENCES `tb_temas`(`id`);

ALTER TABLE `tb_postagens` ADD CONSTRAINT `tb_postagens_fk1` FOREIGN KEY (`usuario_id`) REFERENCES `tb_usuarios`(`id`);




