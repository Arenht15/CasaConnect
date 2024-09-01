-- --------------------------------------------------------
-- Host:                         localhost
-- Versión del servidor:         PostgreSQL 14.13 (Ubuntu 14.13-0ubuntu0.22.04.1) on x86_64-pc-linux-gnu, compiled by gcc (Ubuntu 11.4.0-1ubuntu1~22.04) 11.4.0, 64-bit
-- SO del servidor:              
-- HeidiSQL Versión:             12.7.0.6850
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES  */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

-- Volcando datos para la tabla public.vivienda: -1 rows
/*!40000 ALTER TABLE "vivienda" DISABLE KEYS */;
INSERT INTO "vivienda" ("estado", "intencion_venta", "numero_de_habitaciones", "precio", "id_vendedor", "id_vivienda", "codigo_unico", "descripcion", "tipo_vivienda", "ubicacion", "titulo", "numero_de_banos", "metros_cuadrados") VALUES
	(1, 1, 3, 20000000, 2, 1, 'casa1', 'Hermosa casa moderna ubicada en el corazón de Santiago. Perfecta para familias jóvenes o profesionales que buscan comodidad y estilo en el centro de la ciudad.', 'casa', 'Santiago Centro, Chile', 'Hermosa casa moderna', 2, 83),
	(1, 0, 2, 300000, 2, 2, 'casa2', 'Hermosa casa estilo colonial ubicada en el corazón de Barrio Yungay. Perfecta para familias o profesionales que buscan comodidad y estilo en el centro de la ciudad.', 'casa', 'Santiago Centro, Chile', 'Hermoso chalet', 2, 70),
	(1, 0, 2, 350000, 2, 3, 'depa2', 'Departamento 65 mts2. Barrio tranquilo, bajos gastos comunes.', 'departamento', 'Santiago Centro, Chile', 'Departamento Barrio Yungay', 2, 65)	;
/*!40000 ALTER TABLE "vivienda" ENABLE KEYS */;

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
