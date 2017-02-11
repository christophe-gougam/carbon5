-- phpMyAdmin SQL Dump
-- version 3.3.9
-- http://www.phpmyadmin.net
--
-- Serveur: localhost
-- Généré le : Sam 11 Février 2017 à 04:23
-- Version du serveur: 5.1.53
-- Version de PHP: 5.3.4

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données: `carbone5`
--

-- --------------------------------------------------------

--
-- Structure de la table `carentrance`
--

CREATE TABLE IF NOT EXISTS `carentrance` (
  `IDVehicule` varchar(200) DEFAULT NULL,
  `TypeVehicule` varchar(200) DEFAULT NULL,
  `statut` varchar(200) DEFAULT NULL,
  `NumParking` varchar(200) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Contenu de la table `carentrance`
--

