-- phpMyAdmin SQL Dump
-- version 3.3.9
-- http://www.phpmyadmin.net
--
-- Serveur: localhost
-- Généré le : Mar 07 Mars 2017 à 13:59
-- Version du serveur: 5.1.53
-- Version de PHP: 5.3.4

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données: `carbon5`
--

-- --------------------------------------------------------

--
-- Structure de la table `car`
--

DROP TABLE IF EXISTS `car`;
CREATE TABLE IF NOT EXISTS `car` (
  `NumPuce` varchar(100) NOT NULL,
  `TypeVehicule` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`NumPuce`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Contenu de la table `car`
--


-- --------------------------------------------------------

--
-- Structure de la table `carddefect`
--

DROP TABLE IF EXISTS `carddefect`;
CREATE TABLE IF NOT EXISTS `carddefect` (
  `IdDefect` int(11) NOT NULL DEFAULT '0',
  `IdCard` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`IdDefect`,`IdCard`),
  KEY `fk_Card_CardDefect` (`IdCard`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Contenu de la table `carddefect`
--


-- --------------------------------------------------------

--
-- Structure de la table `cardrepairs`
--

DROP TABLE IF EXISTS `cardrepairs`;
CREATE TABLE IF NOT EXISTS `cardrepairs` (
  `IdRepair` int(11) NOT NULL DEFAULT '0',
  `IdCard` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`IdRepair`,`IdCard`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Contenu de la table `cardrepairs`
--


-- --------------------------------------------------------

--
-- Structure de la table `cardstate`
--

DROP TABLE IF EXISTS `cardstate`;
CREATE TABLE IF NOT EXISTS `cardstate` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Description` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Contenu de la table `cardstate`
--


-- --------------------------------------------------------

--
-- Structure de la table `defect`
--

DROP TABLE IF EXISTS `defect`;
CREATE TABLE IF NOT EXISTS `defect` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Description` text,
  PRIMARY KEY (`Id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Contenu de la table `defect`
--


-- --------------------------------------------------------

--
-- Structure de la table `parking`
--

DROP TABLE IF EXISTS `parking`;
CREATE TABLE IF NOT EXISTS `parking` (
  `NumParking` int(11) NOT NULL AUTO_INCREMENT,
  `NomParking` varchar(50) DEFAULT NULL,
  `Capacity` int(11) DEFAULT NULL,
  PRIMARY KEY (`NumParking`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Contenu de la table `parking`
--


-- --------------------------------------------------------

--
-- Structure de la table `part`
--

DROP TABLE IF EXISTS `part`;
CREATE TABLE IF NOT EXISTS `part` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Stock` int(11) DEFAULT NULL,
  `NamePart` varchar(50) DEFAULT NULL,
  `PurchasePrice` float DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Contenu de la table `part`
--


-- --------------------------------------------------------

--
-- Structure de la table `partdefect`
--

DROP TABLE IF EXISTS `partdefect`;
CREATE TABLE IF NOT EXISTS `partdefect` (
  `IdPart` int(11) NOT NULL DEFAULT '0',
  `IdDefect` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`IdPart`,`IdDefect`),
  KEY `fk_Defect_PartDefect` (`IdDefect`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Contenu de la table `partdefect`
--


-- --------------------------------------------------------

--
-- Structure de la table `partrepairs`
--

DROP TABLE IF EXISTS `partrepairs`;
CREATE TABLE IF NOT EXISTS `partrepairs` (
  `IdPart` int(11) NOT NULL DEFAULT '0',
  `IdRepair` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`IdPart`,`IdRepair`),
  KEY `fk_Repair_PartRepairs` (`IdRepair`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Contenu de la table `partrepairs`
--


-- --------------------------------------------------------

--
-- Structure de la table `place`
--

DROP TABLE IF EXISTS `place`;
CREATE TABLE IF NOT EXISTS `place` (
  `NumPlace` int(11) NOT NULL,
  `NumPark` int(11) DEFAULT NULL,
  `IsOccupied` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`NumPlace`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Contenu de la table `place`
--


-- --------------------------------------------------------

--
-- Structure de la table `repaircard`
--

DROP TABLE IF EXISTS `repaircard`;
CREATE TABLE IF NOT EXISTS `repaircard` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `IdDegree` int(11) DEFAULT NULL,
  `IdState` int(11) DEFAULT NULL,
  `IdCar` int(11) DEFAULT NULL,
  `IdCard` int(11) DEFAULT NULL,
  `IdParkPlace` int(11) DEFAULT NULL,
  `EntryDate` date DEFAULT NULL,
  `OutDate` date DEFAULT NULL,
  `OverAllDetails` text,
  `IdUser` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `fk_Urgency_RepairCard` (`IdDegree`),
  KEY `fk_CardState_RepairCard` (`IdCard`),
  KEY `fk_Car_RepairCard` (`IdCar`),
  KEY `fk_ParkPlace_RepairCard` (`IdParkPlace`),
  KEY `fk_User_RepairCard` (`IdUser`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Contenu de la table `repaircard`
--


-- --------------------------------------------------------

--
-- Structure de la table `repairs`
--

DROP TABLE IF EXISTS `repairs`;
CREATE TABLE IF NOT EXISTS `repairs` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `DateRepair` date DEFAULT NULL,
  `Nature` varchar(50) DEFAULT NULL,
  `TimeSpent` float DEFAULT NULL,
  `Description` text,
  PRIMARY KEY (`Id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Contenu de la table `repairs`
--


-- --------------------------------------------------------

--
-- Structure de la table `typeuser`
--

DROP TABLE IF EXISTS `typeuser`;
CREATE TABLE IF NOT EXISTS `typeuser` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Profil` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Contenu de la table `typeuser`
--


-- --------------------------------------------------------

--
-- Structure de la table `urgencydegree`
--

DROP TABLE IF EXISTS `urgencydegree`;
CREATE TABLE IF NOT EXISTS `urgencydegree` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Description` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Contenu de la table `urgencydegree`
--


-- --------------------------------------------------------

--
-- Structure de la table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `TypeUser` int(11) DEFAULT NULL,
  `FirstName` varchar(50) DEFAULT NULL,
  `LastName` varchar(50) DEFAULT NULL,
  `DateOfBirth` date DEFAULT NULL,
  `Address` varchar(50) DEFAULT NULL,
  `Town` varchar(50) DEFAULT NULL,
  `PostalCode` int(11) DEFAULT NULL,
  `Login` varchar(50) DEFAULT NULL,
  `PasswordUser` varchar(50) DEFAULT NULL,
  `Email` varchar(50) DEFAULT NULL,
  `HiringDate` date DEFAULT NULL,
  `IncomingPerHour` float DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `fk_Type` (`TypeUser`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Contenu de la table `users`
--

