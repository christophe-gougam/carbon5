-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Client :  127.0.0.1
-- Généré le :  Lun 08 Mai 2017 à 21:35
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

-- --------------------------------------------------------

--
-- Structure de la table `carddefect`
--

CREATE TABLE `carddefect` (
  `IdDefect` int(11) NOT NULL,
  `IdCard` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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

-- --------------------------------------------------------

--
-- Structure de la table `defect`
--

CREATE TABLE `defect` (
  `Id` int(11) NOT NULL,
  `Description` text,
  `RepairTime` int(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `defect`
--

INSERT INTO `defect` (`Id`, `Description`, `RepairTime`) VALUES
(1, 'Frein', 2),
(2, 'Levier', 1),
(3, 'Pneu', 0);

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
(1, 25, 'moteur', 25),
(2, 10, 'test', 2);

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
  `NumPark` int(11) DEFAULT NULL,
  `IsOccupied` tinyint(1) DEFAULT NULL,
  `IdParking` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
(1, 'Auto');

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
(1, 1, 'test', 'test', '2017-04-11', 'azertyu', 'azertyu', 92100, 'test', 'test', 'test', '2017-04-03', 2);

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
  ADD PRIMARY KEY (`Id`);

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
  ADD KEY `fk_place` (`IdParking`);

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
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `defect`
--
ALTER TABLE `defect`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT pour la table `parking`
--
ALTER TABLE `parking`
  MODIFY `NumParking` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `part`
--
ALTER TABLE `part`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT pour la table `repaircard`
--
ALTER TABLE `repaircard`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `repairs`
--
ALTER TABLE `repairs`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `typecar`
--
ALTER TABLE `typecar`
  MODIFY `Id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT pour la table `typeuser`
--
ALTER TABLE `typeuser`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT pour la table `urgencydegree`
--
ALTER TABLE `urgencydegree`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `users`
--
ALTER TABLE `users`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
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
  ADD CONSTRAINT `fk_place` FOREIGN KEY (`IdParking`) REFERENCES `parking` (`NumParking`);

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
