-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 09, 2024 at 02:12 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `hemijska_cistionica`
--

-- --------------------------------------------------------

--
-- Table structure for table `klijent`
--

CREATE TABLE `klijent` (
  `id` int(11) NOT NULL,
  `ime_prezime` varchar(50) NOT NULL,
  `poslovi` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `klijent`
--

INSERT INTO `klijent` (`id`, `ime_prezime`, `poslovi`) VALUES
(1, 'Nikola Nikolic', 11),
(2, 'Jovan Jovanovic', 1),
(3, 'Pero Peric', 1),
(4, 'Jovana Jovanovic', 1),
(5, 'Ivana Ivanovic', 1);

-- --------------------------------------------------------

--
-- Table structure for table `posao`
--

CREATE TABLE `posao` (
  `id` int(11) NOT NULL,
  `datum_vrijeme` datetime NOT NULL,
  `radnik_id` int(11) NOT NULL,
  `usluga_id` int(11) NOT NULL,
  `klijent_id` int(11) NOT NULL,
  `gotovo` tinyint(1) NOT NULL,
  `popust` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `posao`
--

INSERT INTO `posao` (`id`, `datum_vrijeme`, `radnik_id`, `usluga_id`, `klijent_id`, `gotovo`, `popust`) VALUES
(1, '2024-06-06 14:40:30', 1, 1, 1, 1, 0),
(2, '2024-06-08 14:50:28', 1, 2, 2, 0, 0),
(3, '2024-06-08 15:19:28', 1, 1, 1, 0, 0),
(4, '2024-06-09 13:31:50', 1, 3, 3, 0, 0),
(5, '2024-06-09 13:32:31', 2, 8, 4, 0, 0),
(6, '2024-06-09 13:32:45', 2, 15, 5, 0, 0),
(7, '2024-06-09 13:55:20', 1, 8, 1, 0, 1),
(8, '2024-06-09 13:56:11', 1, 4, 1, 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `radnik`
--

CREATE TABLE `radnik` (
  `id` int(11) NOT NULL,
  `ime_prezime` varchar(50) NOT NULL,
  `korisnicko_ime` varchar(50) NOT NULL,
  `lozinka` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `radnik`
--

INSERT INTO `radnik` (`id`, `ime_prezime`, `korisnicko_ime`, `lozinka`) VALUES
(1, 'Predrag Lazarevic', 'predrag.lazarevic', '683da4446af6a041879f85a11fe9f522f9aa337f2706f5f31c557a1df5c33592'),
(2, 'Petar Petrovic', 'petar.petrovic', '6fa8bd9f5fe8ad5ce091b699b64b4bac6e8b2369128c363559458632649e7735');

-- --------------------------------------------------------

--
-- Table structure for table `usluga`
--

CREATE TABLE `usluga` (
  `id` int(11) NOT NULL,
  `naziv` varchar(50) NOT NULL,
  `cijena` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `usluga`
--

INSERT INTO `usluga` (`id`, `naziv`, `cijena`) VALUES
(1, 'Kaput', 20),
(2, 'Mantil', 20),
(3, 'Odjelo', 23),
(4, 'Sako', 12),
(5, 'Pantalone', 11),
(6, 'Jakna tanka', 19),
(7, 'Jakna pernata', 22),
(8, 'Jakna zimska', 25),
(9, 'Mantil pernati', 25),
(10, 'Prsluk', 5),
(11, 'Košulja', 5),
(12, 'Kravata', 5),
(13, 'Džemper', 7),
(14, 'Majica', 5),
(15, 'Haljina', 15),
(16, 'Vjenčanica', 50),
(17, 'Bunda sintetička', 40),
(18, 'Bunda prirodna', 60),
(19, 'Kapa', 5),
(20, 'Rukavice', 10),
(21, 'Šal', 5);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `klijent`
--
ALTER TABLE `klijent`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `posao`
--
ALTER TABLE `posao`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `radnik`
--
ALTER TABLE `radnik`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `usluga`
--
ALTER TABLE `usluga`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `klijent`
--
ALTER TABLE `klijent`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `posao`
--
ALTER TABLE `posao`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `radnik`
--
ALTER TABLE `radnik`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `usluga`
--
ALTER TABLE `usluga`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
