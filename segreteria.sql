-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Creato il: Feb 05, 2024 alle 10:42
-- Versione del server: 10.4.28-MariaDB
-- Versione PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `segreteria`
--
CREATE DATABASE IF NOT EXISTS `segreteria` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `segreteria`;

-- --------------------------------------------------------

--
-- Struttura della tabella `bookingExams`
--

CREATE TABLE `bookingExams` (
  `idPrenotazione` int(11) NOT NULL,
  `idEsamePre` int(11) NOT NULL,
  `matricolaStudente` bigint(10) NOT NULL
  FOREIGN KEY ('idEsamePre')
  FOREIGN KEY ('matricolaStudente')
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Struttura della tabella `domandeQuest`
--

CREATE TABLE `domandeQuest` (
  `idDomanda` int(11) NOT NULL,
  `matricolaDomanda` bigint(10) NOT NULL,
  `EsameDomanda` varchar(30) NOT NULL,
  `domanda1` varchar(15) NOT NULL,
  `domanda2` varchar(15) NOT NULL,
  `domanda3` varchar(15) NOT NULL
  FOREIGN KEY ('matricolaDomanda')
  FOREIGN KEY ('EsameDomanda')
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `domandeQuest`
--

INSERT INTO `domandeQuest` (`idDomanda`, `matricolaDomanda`, `EsameDomanda`, `domanda1`, `domanda2`, `domanda3`) VALUES
(3, 1111111111, 'Programmazione III', 'buone', 'si', 'si');

-- --------------------------------------------------------

--
-- Struttura della tabella `exams`
--

CREATE TABLE `exams` (
  `idEsame` int(11) NOT NULL,
  `nomeEsame` varchar(30) NOT NULL,
  `dataEsame` date NOT NULL,
  `orarioEsame` varchar(5) NOT NULL,
  `aulaEsame` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `exams`
--

INSERT INTO `exams` (`idEsame`, `nomeEsame`, `dataEsame`, `orarioEsame`, `aulaEsame`) VALUES
(100960, 'Programmazione II', '2024-02-08', '09:30', '12');

-- --------------------------------------------------------

--
-- Struttura della tabella `questionari`
--

CREATE TABLE `questionari` (
  `idQuest` int(11) NOT NULL,
  `nomeExQuest` varchar(40) NOT NULL,
  `effettuato` tinyint(1) NOT NULL
  FOREIGN KEY ('nomeExQuest')
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `questionari`
--

INSERT INTO `questionari` (`idQuest`, `nomeExQuest`, `effettuato`) VALUES
(1, 'Programmazione I', 0),
(2, 'Programmazione II', 0),
(3, 'Programmazione III', 0);

-- --------------------------------------------------------

--
-- Struttura della tabella `secretary`
--

CREATE TABLE `secretary` (
  `id` int(11) NOT NULL,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `secretary`
--

INSERT INTO `secretary` (`id`, `username`, `password`) VALUES
(1, 'admin', '1234');

-- --------------------------------------------------------

--
-- Struttura della tabella `students`
--

CREATE TABLE `students` (
  `matricola` bigint(10) NOT NULL,
  `nome` varchar(20) NOT NULL,
  `cognome` varchar(20) NOT NULL,
  `dataDiNascita` date NOT NULL,
  `residenza` varchar(15) NOT NULL,
  `pianoDiStudi` varchar(50) NOT NULL,
  `password` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `students`
--

INSERT INTO `students` (`matricola`, `nome`, `cognome`, `dataDiNascita`, `residenza`, `pianoDiStudi`, `password`) VALUES
(1111111111, 'Andrea', 'Aristarco', '2000-08-08', 'Napoli', 'Informatica', '123456789'),
(3378730075, 'Carmine', 'Simeoli', '1999-02-24', 'Roma', 'Scienze Biologiche', 'abcd726545'),
(6557620077, 'Enzo', 'Botta', '2024-02-02', 'Napoli', 'Informatica', 'ciaoatutti');

-- --------------------------------------------------------

--
-- Struttura della tabella `tasse`
--

CREATE TABLE `tasse` (
  `idTassa` int(11) NOT NULL,
  `matricolaTassa` bigint(10) NOT NULL,
  `causale` varchar(50) NOT NULL,
  `importo` double NOT NULL,
  `dataScadenza` date NOT NULL,
  `pagata` tinyint(1) NOT NULL
  FOREIGN KEY ('matricolaTassa')
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `tasse`
--

INSERT INTO `tasse` (`idTassa`, `matricolaTassa`, `causale`, `importo`, `dataScadenza`, `pagata`) VALUES
(111122, 6557620077, 'Tassa d iscrizione 1-3', 120, '2024-01-31', 0),
(610214, 3378730075, 'Tassa d iscrizione 1-3', 120, '2024-01-31', 0),
(717043, 1111111111, 'Tassa d iscrizione 1-3', 120, '2024-01-31', 0),
(828224, 6557620077, 'Tassa d iscrizione 2-3', 60, '2024-04-30', 0),
(844903, 1111111111, 'Tassa d iscrizione 3-3', 60, '2024-09-30', 0),
(936007, 3378730075, 'Tassa d iscrizione 2-3', 60, '2024-04-30', 0),
(993458, 6557620077, 'Tassa d iscrizione 3-3', 60, '2024-09-30', 0),
(1033862, 1111111111, 'Tassa d iscrizione 2-3', 60, '2024-04-30', 0),
(1052772, 3378730075, 'Tassa d iscrizione 3-3', 60, '2024-09-30', 0);

-- --------------------------------------------------------

--
-- Struttura della tabella `teachers`
--

CREATE TABLE `teachers` (
  `id` int(5) NOT NULL,
  `nome` varchar(20) NOT NULL,
  `cognome` varchar(20) NOT NULL,
  `pianoDiStudi` varchar(50) NOT NULL,
  `password` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `teachers`
--

INSERT INTO `teachers` (`id`, `nome`, `cognome`, `pianoDiStudi`, `password`) VALUES
(12345, 'Roberto', 'Carraturo', 'Informatica', '1234567899'),
(12456, 'Salvatore', 'Esposito', 'Scienze Biologiche', '987654321'),
(98464, 'Carmine', 'Scognamiglio', 'Conduzione del Mezzo Navale', '2143658709');

-- --------------------------------------------------------

--
-- Struttura della tabella `voti`
--

CREATE TABLE `voti` (
  `idVoto` int(11) NOT NULL,
  `idPreVoto` int(11) NOT NULL,
  `idEsameVoto` int(11) NOT NULL,
  `nomeExVoto` varchar(30) NOT NULL,
  `matricolaVoto` bigint(10) NOT NULL,
  `voto` int(2) NOT NULL,
  `conferma` tinyint(1) NOT NULL
  FOREIGN KEY ('idPreVoto')
  FOREIGN KEY ('idEsameVoto')
  FOREIGN KEY ('nomeExVoto')
  FOREIGN KEY ('matricolaVoto')
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `voti`
--

INSERT INTO `voti` (`idVoto`, `idPreVoto`, `idEsameVoto`, `nomeExVoto`, `matricolaVoto`, `voto`, `conferma`) VALUES
(857, 102842, 25438, 'Programmazione I', 1111111111, 25, 1),
(1016, 62066, 27211, 'Programmazione III', 1111111111, 26, 1);

--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle `bookingExams`
--
ALTER TABLE `bookingExams`
  ADD PRIMARY KEY (`idPrenotazione`);

--
-- Indici per le tabelle `domandeQuest`
--
ALTER TABLE `domandeQuest`
  ADD PRIMARY KEY (`idDomanda`);

--
-- Indici per le tabelle `exams`
--
ALTER TABLE `exams`
  ADD PRIMARY KEY (`idEsame`);

--
-- Indici per le tabelle `questionari`
--
ALTER TABLE `questionari`
  ADD PRIMARY KEY (`idQuest`);

--
-- Indici per le tabelle `secretary`
--
ALTER TABLE `secretary`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `students`
--
ALTER TABLE `students`
  ADD PRIMARY KEY (`matricola`);

--
-- Indici per le tabelle `tasse`
--
ALTER TABLE `tasse`
  ADD PRIMARY KEY (`idTassa`);

--
-- Indici per le tabelle `teachers`
--
ALTER TABLE `teachers`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `voti`
--
ALTER TABLE `voti`
  ADD PRIMARY KEY (`idVoto`);

--
-- AUTO_INCREMENT per le tabelle scaricate
--

--
-- AUTO_INCREMENT per la tabella `domandeQuest`
--
ALTER TABLE `domandeQuest`
  MODIFY `idDomanda` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT per la tabella `exams`
--
ALTER TABLE `exams`
  MODIFY `idEsame` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=106268;

--
-- AUTO_INCREMENT per la tabella `questionari`
--
ALTER TABLE `questionari`
  MODIFY `idQuest` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT per la tabella `secretary`
--
ALTER TABLE `secretary`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
