-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Client :  127.0.0.1
-- Généré le :  Lun 05 Juin 2017 à 11:54
-- Version du serveur :  5.7.14
-- Version de PHP :  5.6.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `carbon5`
--

-- --------------------------------------------------------

--
-- Structure de la table `car`
--

CREATE TABLE `car` (
  `NumPuce` varchar(100) NOT NULL,
  `TypeVehicule` varchar(50) DEFAULT NULL,
  `matricule` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `car`
--

INSERT INTO `car` (`NumPuce`, `TypeVehicule`, `matricule`) VALUES
('l78p', 'Velo', '1234'),
('VEL123', 'Velo', '123123'),
('VEL124', 'Velo', '123124'),
('VOI456', 'Voiture', '456456'),
('VOI458', 'Voiture', '456458');

-- --------------------------------------------------------

--
-- Structure de la table `carddefect`
--

CREATE TABLE `carddefect` (
  `IdDefect` int(11) NOT NULL,
  `IdCard` int(11) NOT NULL,
  `fixed` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `carddefect`
--

INSERT INTO `carddefect` (`IdDefect`, `IdCard`, `fixed`) VALUES
(1, 3, 0),
(3, 1, 0),
(3, 3, 0),
(7, 2, 0),
(8, 2, 0),
(12, 4, 0),
(16, 4, 0),
(20, 1, 0),
(22, 2, 0),
(34, 1, 0);

-- --------------------------------------------------------

--
-- Structure de la table `cardrepairs`
--

CREATE TABLE `cardrepairs` (
  `IdRepair` int(11) NOT NULL,
  `IdCard` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `cardstate`
--

CREATE TABLE `cardstate` (
  `Id` int(11) NOT NULL,
  `Description` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `cardstate`
--

INSERT INTO `cardstate` (`Id`, `Description`) VALUES
(1, 'En attente'),
(3, 'En cours'),
(5, 'Reparé');

-- --------------------------------------------------------

--
-- Structure de la table `defect`
--

CREATE TABLE `defect` (
  `Id` int(11) NOT NULL,
  `Description` text,
  `RepairTime` double NOT NULL,
  `criticity` int(11) DEFAULT NULL,
  `partForRepair` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `defect`
--

INSERT INTO `defect` (`Id`, `Description`, `RepairTime`, `criticity`, `partForRepair`) VALUES
(1, 'Frein', 1.5, 3, 9),
(2, 'Levier', 0.5, 2, 27),
(3, 'Pneu', 0, 2, 3),
(5, 'Electronique', 10, 3, 26),
(6, 'Batterie', 1, 2, 24),
(7, 'Boite de vitesse', 6, 4, 28),
(8, 'Plaque Imatriculation', 9, 1, 29),
(10, 'CLimatisation', 2, 1, 18),
(11, 'Geometrie', 1, 1, 13),
(12, 'Revision', 0, 1, 16),
(13, 'Vidange', 4, 1, 15),
(14, 'Filtration', 10, 3, 23),
(16, 'Eclairage', 6, 1, 14),
(17, 'Atelage', 9, 4, 23),
(18, 'Distribution', 7, 4, 4),
(19, 'Prestation', 10, 2, 26),
(20, 'Echappement', 1, 3, 21),
(21, 'SystÃ¨me de freinage', 6, 5, 1),
(22, 'Controle', 9, 2, 26),
(23, 'Diagnostics', 7, 2, 23),
(24, 'EntretienEchappement', 2, 2, 23),
(25, 'MontageAmortisseur', 1, 4, 11),
(26, 'MontageBatterie', 0, 2, 23),
(27, 'ChangerHydrolique', 4, 1, 23),
(28, 'NettoyerFiltreAir', 10, 1, 8),
(29, 'ChangementRoues', 1, 2, 3),
(30, 'ReglageEclairage', 6, 1, 19),
(31, 'RenovationPhare', 9, 2, 23),
(32, 'ServiceCarteGrise', 7, 1, 21),
(33, 'VidangeBoiteEtPont', 10, 3, 19),
(34, 'Moteur', 5, 5, 1);

-- --------------------------------------------------------

--
-- Structure de la table `orderpart`
--

CREATE TABLE `orderpart` (
  `IdPart` int(11) NOT NULL,
  `IdUser` int(11) NOT NULL,
  `Qte` int(11) NOT NULL,
  `date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `orderpart`
--

INSERT INTO `orderpart` (`IdPart`, `IdUser`, `Qte`, `date`) VALUES
(1, 1, -26, '2017-04-19'),
(1, 1, 50, '2017-04-19');

-- --------------------------------------------------------

--
-- Structure de la table `parking`
--

CREATE TABLE `parking` (
  `NumParking` int(11) NOT NULL,
  `NomParking` varchar(50) DEFAULT NULL,
  `Capacity` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `parking`
--

INSERT INTO `parking` (`NumParking`, `NomParking`, `Capacity`) VALUES
(1, 'Vehicule entrant', 1000),
(2, 'Vehicule repare', 1000);

-- --------------------------------------------------------

--
-- Structure de la table `part`
--

CREATE TABLE `part` (
  `Id` int(11) NOT NULL,
  `Stock` int(11) DEFAULT NULL,
  `NamePart` varchar(50) DEFAULT NULL,
  `PurchasePrice` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `part`
--

INSERT INTO `part` (`Id`, `Stock`, `NamePart`, `PurchasePrice`) VALUES
(1, 23, 'moteur', 25),
(2, 0, 'test', 2),
(3, 1, 'pneu', 24),
(4, 19, 'lampe', 10),
(5, 51, 'retroviseur', 10),
(6, 24, 'pedale', 20),
(7, 1, 'chaine', 6),
(8, 14, 'roue', 2),
(9, 1, 'frein', 50),
(10, 1, 'porte', 36),
(11, 1, 'antenne', 49),
(12, 1, 'piston', 102),
(13, 1, 'vitrine', 21),
(14, 1, 'test', 24),
(15, 1, 'ghost', 22),
(16, 1, 'fast', 56),
(17, 1, 'aller', 8),
(18, 1, 'cac', 5),
(19, 1, 'volant', 45),
(21, 1, 'cle', 2),
(23, 1, 'porte baggage', 9),
(24, 1, 'baterie', 200),
(26, 1, 'fils electrique', 15),
(27, 13, 'Levier', 50),
(28, 25, 'Boite de vitesse', 200),
(29, 40, 'Plaque', 10);

-- --------------------------------------------------------

--
-- Structure de la table `partdefect`
--

CREATE TABLE `partdefect` (
  `IdPart` int(11) NOT NULL,
  `IdDefect` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `partrepairs`
--

CREATE TABLE `partrepairs` (
  `IdPart` int(11) NOT NULL,
  `IdRepair` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `place`
--

CREATE TABLE `place` (
  `NumPlace` int(11) NOT NULL,
  `IsOccupied` tinyint(1) DEFAULT NULL,
  `NumPark` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `place`
--

INSERT INTO `place` (`NumPlace`, `IsOccupied`, `NumPark`) VALUES
(1001, 1, 1),
(1005, 1, 1),
(1007, 1, 1),
(1010, 1, 1),
(1011, 0, 1),
(1012, 0, 1),
(1013, 0, 1),
(1014, 1, 1),
(1015, 0, 1),
(1016, 0, 1),
(1017, 0, 1),
(1018, 0, 1),
(1019, 0, 1),
(1020, 0, 1),
(1021, 0, 1),
(1022, 0, 1),
(1023, 0, 1),
(1024, 0, 1),
(1025, 0, 1),
(1026, 0, 1),
(2001, 0, 2),
(2005, 1, 2),
(2007, 0, 2),
(2010, 1, 2),
(2011, 1, 2),
(2012, 1, 2),
(2013, 1, 2),
(2014, 1, 2),
(2015, 1, 2),
(2016, 1, 2),
(2017, 1, 2),
(2018, 1, 2),
(2019, 1, 2),
(2020, 0, 2),
(2021, 0, 2),
(2022, 0, 2),
(2023, 0, 2),
(2024, 0, 2),
(2025, 0, 2),
(2026, 0, 2);

-- --------------------------------------------------------

--
-- Structure de la table `preferences`
--

CREATE TABLE `preferences` (
  `id` int(11) NOT NULL,
  `indifDays` int(11) DEFAULT NULL,
  `vetoDays` int(11) DEFAULT NULL,
  `indifTimeRep` int(11) DEFAULT NULL,
  `vetoTimeRep` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `preferences`
--

INSERT INTO `preferences` (`id`, `indifDays`, `vetoDays`, `indifTimeRep`, `vetoTimeRep`) VALUES
(1, 2, 5, 2, 5);

-- --------------------------------------------------------

--
-- Structure de la table `repaircard`
--

CREATE TABLE `repaircard` (
  `Id` int(11) NOT NULL,
  `IdDegree` int(11) DEFAULT NULL,
  `IdCard` int(11) DEFAULT NULL,
  `IdCar` varchar(100) DEFAULT NULL,
  `IdParkPlace` int(11) DEFAULT NULL,
  `EntryDate` date DEFAULT NULL,
  `OutDate` date DEFAULT NULL,
  `OverAllDetails` text,
  `IdUser` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `repaircard`
--

INSERT INTO `repaircard` (`Id`, `IdDegree`, `IdCard`, `IdCar`, `IdParkPlace`, `EntryDate`, `OutDate`, `OverAllDetails`, `IdUser`) VALUES
(1, 3, 1, 'VOI456', 1001, '2017-03-01', NULL, 'blabla test', 1),
(2, 3, 1, 'VOI458', 1005, '2017-04-01', NULL, 'blabla test2', 1),
(3, 3, 1, 'VEL123', 1007, '2017-05-01', NULL, 'bla bla test3', 1),
(4, 3, 1, 'VEL124', 1010, '2017-06-01', NULL, 'blabla test4', 1);

-- --------------------------------------------------------

--
-- Structure de la table `repairs`
--

CREATE TABLE `repairs` (
  `Id` int(11) NOT NULL,
  `DateRepair` date DEFAULT NULL,
  `Nature` varchar(50) DEFAULT NULL,
  `TimeSpent` float DEFAULT NULL,
  `Description` text
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `typecar`
--

CREATE TABLE `typecar` (
  `Id` int(10) NOT NULL,
  `Type` varchar(50) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Contenu de la table `typecar`
--

INSERT INTO `typecar` (`Id`, `Type`) VALUES
(1, 'Voiture'),
(2, 'Velo');

-- --------------------------------------------------------

--
-- Structure de la table `typeuser`
--

CREATE TABLE `typeuser` (
  `Id` int(11) NOT NULL,
  `Profil` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `typeuser`
--

INSERT INTO `typeuser` (`Id`, `Profil`) VALUES
(1, 'manager'),
(2, 'ouvrier');

-- --------------------------------------------------------

--
-- Structure de la table `urgencydegree`
--

CREATE TABLE `urgencydegree` (
  `Id` int(11) NOT NULL,
  `Description` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `urgencydegree`
--

INSERT INTO `urgencydegree` (`Id`, `Description`) VALUES
(1, 'Très urgent'),
(2, 'Urgent'),
(3, 'Normal'),
(4, 'Peu urgent'),
(5, 'Pas urgent');

-- --------------------------------------------------------

--
-- Structure de la table `users`
--

CREATE TABLE `users` (
  `Id` int(11) NOT NULL,
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
  `IncomingPerHour` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `users`
--

INSERT INTO `users` (`Id`, `TypeUser`, `FirstName`, `LastName`, `DateOfBirth`, `Address`, `Town`, `PostalCode`, `Login`, `PasswordUser`, `Email`, `HiringDate`, `IncomingPerHour`) VALUES
(1, 2, 'test', 'test', '2017-04-11', 'azertyu', 'azertyu', 92100, 'test', 'test', 'test', '2017-04-03', 2),
(2, 1, 'john', 'doe', '2017-04-04', 'azerty', 'azerty', 94000, 'boss', 'boss', 'azrryt', '2017-06-12', 1);

--
-- Index pour les tables exportées
--

--
-- Index pour la table `car`
--
ALTER TABLE `car`
  ADD PRIMARY KEY (`NumPuce`),
  ADD KEY `fk_typeV` (`TypeVehicule`) USING BTREE;

--
-- Index pour la table `carddefect`
--
ALTER TABLE `carddefect`
  ADD PRIMARY KEY (`IdDefect`,`IdCard`),
  ADD KEY `fk_Card_CardDefect` (`IdCard`);

--
-- Index pour la table `cardrepairs`
--
ALTER TABLE `cardrepairs`
  ADD PRIMARY KEY (`IdRepair`,`IdCard`),
  ADD KEY `fk_Card_CardRepair` (`IdCard`);

--
-- Index pour la table `cardstate`
--
ALTER TABLE `cardstate`
  ADD PRIMARY KEY (`Id`);

--
-- Index pour la table `defect`
--
ALTER TABLE `defect`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `fk_part` (`partForRepair`);

--
-- Index pour la table `orderpart`
--
ALTER TABLE `orderpart`
  ADD PRIMARY KEY (`IdPart`,`IdUser`,`Qte`,`date`),
  ADD KEY `fk_User_OrderPart` (`IdUser`);

--
-- Index pour la table `parking`
--
ALTER TABLE `parking`
  ADD PRIMARY KEY (`NumParking`);

--
-- Index pour la table `part`
--
ALTER TABLE `part`
  ADD PRIMARY KEY (`Id`);

--
-- Index pour la table `partdefect`
--
ALTER TABLE `partdefect`
  ADD PRIMARY KEY (`IdPart`,`IdDefect`),
  ADD KEY `fk_Defect_PartDefect` (`IdDefect`);

--
-- Index pour la table `partrepairs`
--
ALTER TABLE `partrepairs`
  ADD PRIMARY KEY (`IdPart`,`IdRepair`),
  ADD KEY `fk_Repair_PartRepairs` (`IdRepair`);

--
-- Index pour la table `place`
--
ALTER TABLE `place`
  ADD PRIMARY KEY (`NumPlace`),
  ADD KEY `fk_place` (`NumPark`);

--
-- Index pour la table `preferences`
--
ALTER TABLE `preferences`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `repaircard`
--
ALTER TABLE `repaircard`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `fk_Urgency_RepairCard` (`IdDegree`),
  ADD KEY `fk_CardState_RepairCard` (`IdCard`),
  ADD KEY `fk_Car_RepairCard` (`IdCar`),
  ADD KEY `fk_ParkPlace_RepairCard` (`IdParkPlace`),
  ADD KEY `fk_User_RepairCard` (`IdUser`);

--
-- Index pour la table `repairs`
--
ALTER TABLE `repairs`
  ADD PRIMARY KEY (`Id`);

--
-- Index pour la table `typecar`
--
ALTER TABLE `typecar`
  ADD PRIMARY KEY (`Id`);

--
-- Index pour la table `typeuser`
--
ALTER TABLE `typeuser`
  ADD PRIMARY KEY (`Id`);

--
-- Index pour la table `urgencydegree`
--
ALTER TABLE `urgencydegree`
  ADD PRIMARY KEY (`Id`);

--
-- Index pour la table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `fk_Type` (`TypeUser`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `cardstate`
--
ALTER TABLE `cardstate`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT pour la table `defect`
--
ALTER TABLE `defect`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=35;
--
-- AUTO_INCREMENT pour la table `parking`
--
ALTER TABLE `parking`
  MODIFY `NumParking` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT pour la table `part`
--
ALTER TABLE `part`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;
--
-- AUTO_INCREMENT pour la table `repaircard`
--
ALTER TABLE `repaircard`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT pour la table `repairs`
--
ALTER TABLE `repairs`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `typecar`
--
ALTER TABLE `typecar`
  MODIFY `Id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT pour la table `typeuser`
--
ALTER TABLE `typeuser`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT pour la table `urgencydegree`
--
ALTER TABLE `urgencydegree`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT pour la table `users`
--
ALTER TABLE `users`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `carddefect`
--
ALTER TABLE `carddefect`
  ADD CONSTRAINT `fk_Card_CardDefect` FOREIGN KEY (`IdCard`) REFERENCES `repaircard` (`Id`),
  ADD CONSTRAINT `fk_Defect_CardDefect` FOREIGN KEY (`IdDefect`) REFERENCES `defect` (`Id`);

--
-- Contraintes pour la table `cardrepairs`
--
ALTER TABLE `cardrepairs`
  ADD CONSTRAINT `fk_Card_CardRepair` FOREIGN KEY (`IdCard`) REFERENCES `repaircard` (`Id`),
  ADD CONSTRAINT `fk_Repairs_CardRepair` FOREIGN KEY (`IdRepair`) REFERENCES `repairs` (`Id`);

--
-- Contraintes pour la table `defect`
--
ALTER TABLE `defect`
  ADD CONSTRAINT `fk_part` FOREIGN KEY (`partForRepair`) REFERENCES `part` (`Id`);

--
-- Contraintes pour la table `orderpart`
--
ALTER TABLE `orderpart`
  ADD CONSTRAINT `fk_Part_OrderPart` FOREIGN KEY (`IdPart`) REFERENCES `part` (`Id`),
  ADD CONSTRAINT `fk_User_OrderPart` FOREIGN KEY (`IdUser`) REFERENCES `users` (`Id`);

--
-- Contraintes pour la table `partdefect`
--
ALTER TABLE `partdefect`
  ADD CONSTRAINT `fk_Defect_PartDefect` FOREIGN KEY (`IdDefect`) REFERENCES `defect` (`Id`),
  ADD CONSTRAINT `fk_Part_PartDefect` FOREIGN KEY (`IdPart`) REFERENCES `part` (`Id`);

--
-- Contraintes pour la table `partrepairs`
--
ALTER TABLE `partrepairs`
  ADD CONSTRAINT `fk_Part_PartRepairs` FOREIGN KEY (`IdPart`) REFERENCES `part` (`Id`),
  ADD CONSTRAINT `fk_Repair_PartRepairs` FOREIGN KEY (`IdRepair`) REFERENCES `repairs` (`Id`);

--
-- Contraintes pour la table `place`
--
ALTER TABLE `place`
  ADD CONSTRAINT `fk_place` FOREIGN KEY (`NumPark`) REFERENCES `parking` (`NumParking`);

--
-- Contraintes pour la table `repaircard`
--
ALTER TABLE `repaircard`
  ADD CONSTRAINT `fk_Car_RepairCard` FOREIGN KEY (`IdCar`) REFERENCES `car` (`NumPuce`),
  ADD CONSTRAINT `fk_CardState_RepairCard` FOREIGN KEY (`IdCard`) REFERENCES `cardstate` (`Id`),
  ADD CONSTRAINT `fk_ParkPlace_RepairCard` FOREIGN KEY (`IdParkPlace`) REFERENCES `place` (`NumPlace`),
  ADD CONSTRAINT `fk_Urgency_RepairCard` FOREIGN KEY (`IdDegree`) REFERENCES `urgencydegree` (`Id`),
  ADD CONSTRAINT `fk_User_RepairCard` FOREIGN KEY (`IdUser`) REFERENCES `users` (`Id`);

--
-- Contraintes pour la table `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `fk_Type` FOREIGN KEY (`TypeUser`) REFERENCES `typeuser` (`Id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
