-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th6 01, 2017 lúc 09:27 CH
-- Phiên bản máy phục vụ: 5.7.14
-- Phiên bản PHP: 5.6.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `carbon5`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `car`
--

CREATE TABLE `car` (
  `NumPuce` varchar(100) NOT NULL,
  `TypeVehicule` varchar(50) DEFAULT NULL,
  `matricule` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `car`
--

INSERT INTO `car` (`NumPuce`, `TypeVehicule`, `matricule`) VALUES
('', 'Voiture', ''),
('l78p', 'Velo', '1234'),
('VEL123', 'Velo', '123123'),
('VEL124', 'Velo', '123124'),
('VOI456', 'Voiture', '456456'),
('VOI458', 'Voiture', '456458');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `carddefect`
--

CREATE TABLE `carddefect` (
  `IdDefect` int(11) NOT NULL,
  `IdCard` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `cardrepairs`
--

CREATE TABLE `cardrepairs` (
  `IdRepair` int(11) NOT NULL,
  `IdCard` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `cardstate`
--

CREATE TABLE `cardstate` (
  `Id` int(11) NOT NULL,
  `Description` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `cardstate`
--

INSERT INTO `cardstate` (`Id`, `Description`) VALUES
(1, 'En attente'),
(3, 'En cours'),
(5, 'Reparé');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `defect`
--

CREATE TABLE `defect` (
  `Id` int(11) NOT NULL,
  `Description` text,
  `RepairTime` int(100) NOT NULL,
  `criticity` int(11) DEFAULT NULL,
  `partForRepair` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `defect`
--

INSERT INTO `defect` (`Id`, `Description`, `RepairTime`, `criticity`, `partForRepair`) VALUES
(1, 'Frein', 2, NULL, NULL),
(2, 'Levier', 1, NULL, NULL),
(3, 'Pneu', 0, NULL, NULL),
(4, 'Batterie', 4, NULL, NULL),
(5, 'Electronique', 10, NULL, NULL),
(6, 'Batterie', 1, NULL, NULL),
(7, 'Boite de vitesse', 6, NULL, NULL),
(8, 'Plaque Imatriculation', 9, NULL, NULL),
(9, 'Vidange', 7, NULL, NULL),
(10, 'CLimatisation', 2, NULL, NULL),
(11, 'Geometrie', 1, NULL, NULL),
(12, 'Revision', 0, NULL, NULL),
(13, 'Vidange', 4, NULL, NULL),
(14, 'Filtration', 10, NULL, NULL),
(15, 'Batterie', 1, NULL, NULL),
(16, 'Eclairage', 6, NULL, NULL),
(17, 'Atelage', 9, NULL, NULL),
(18, 'Distribution', 7, NULL, NULL),
(19, 'Prestation', 10, NULL, NULL),
(20, 'Echappement', 1, NULL, NULL),
(21, 'SystÃ¨me de freinage', 6, NULL, NULL),
(22, 'Controle', 9, NULL, NULL),
(23, 'Diagnostics', 7, NULL, NULL),
(24, 'EntretienEchappement', 2, NULL, NULL),
(25, 'MontageAmortisseur', 1, NULL, NULL),
(26, 'MontageBatterie', 0, NULL, NULL),
(27, 'ChangerHydrolique', 4, NULL, NULL),
(28, 'NettoyerFiltreAir', 10, NULL, NULL),
(29, 'ChangementRoues', 1, NULL, NULL),
(30, 'ReglageEclairage', 6, NULL, NULL),
(31, 'RenovationPhare', 9, NULL, NULL),
(32, 'ServiceCarteGrise', 7, NULL, NULL),
(33, 'VidangeBoiteEtPont', 10, NULL, NULL);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `orderpart`
--

CREATE TABLE `orderpart` (
  `IdPart` int(11) NOT NULL,
  `IdUser` int(11) NOT NULL,
  `Qte` int(11) NOT NULL,
  `date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `orderpart`
--

INSERT INTO `orderpart` (`IdPart`, `IdUser`, `Qte`, `date`) VALUES
(1, 1, -26, '2017-04-19'),
(1, 1, 50, '2017-04-19');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `parking`
--

CREATE TABLE `parking` (
  `NumParking` int(11) NOT NULL,
  `NomParking` varchar(50) DEFAULT NULL,
  `Capacity` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `parking`
--

INSERT INTO `parking` (`NumParking`, `NomParking`, `Capacity`) VALUES
(1, 'Vehicule entrant', 1000),
(2, 'Vehicule repare', 1000);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `part`
--

CREATE TABLE `part` (
  `Id` int(11) NOT NULL,
  `Stock` int(11) DEFAULT NULL,
  `NamePart` varchar(50) DEFAULT NULL,
  `PurchasePrice` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `part`
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
(26, 1, 'fils electrique', 15);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `partdefect`
--

CREATE TABLE `partdefect` (
  `IdPart` int(11) NOT NULL,
  `IdDefect` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `partrepairs`
--

CREATE TABLE `partrepairs` (
  `IdPart` int(11) NOT NULL,
  `IdRepair` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `place`
--

CREATE TABLE `place` (
  `NumPlace` int(11) NOT NULL,
  `IsOccupied` tinyint(1) DEFAULT NULL,
  `NumPark` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `place`
--

INSERT INTO `place` (`NumPlace`, `IsOccupied`, `NumPark`) VALUES
(1001, 1, 1),
(1005, 1, 2),
(1007, 1, 1),
(1010, 1, 1),
(1011, 0, 1),
(1012, 0, 1),
(1013, 1, 1),
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
(2005, 2, 2),
(2007, 0, 2),
(2010, 0, 2),
(2011, 0, 2),
(2012, 0, 2),
(2013, 0, 2),
(2014, 0, 2),
(2015, 0, 2),
(2016, 0, 2),
(2017, 0, 2),
(2018, 0, 2),
(2019, 0, 2),
(2020, 0, 2),
(2021, 0, 2),
(2022, 0, 2),
(2023, 0, 2),
(2024, 0, 2),
(2025, 0, 2),
(2026, 0, 2);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `preferences`
--

CREATE TABLE `preferences` (
  `id` int(11) NOT NULL,
  `indifDays` float DEFAULT NULL,
  `vetoDays` float DEFAULT NULL,
  `indifTimeRep` float DEFAULT NULL,
  `vetoTimeRep` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `repaircard`
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
-- Đang đổ dữ liệu cho bảng `repaircard`
--

INSERT INTO `repaircard` (`Id`, `IdDegree`, `IdCard`, `IdCar`, `IdParkPlace`, `EntryDate`, `OutDate`, `OverAllDetails`, `IdUser`) VALUES
(1, 1, 3, 'VEL123', 1013, '2017-06-01', '2017-06-04', 'bla bla bla', 1),
(2, 5, 1, 'VOI458', 2016, '2017-06-15', '2017-06-17', 'laisse tomber', 1),
(3, 2, 5, 'VOI456', 1025, '2017-06-01', '2017-06-01', 'bien fait', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `repairs`
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
-- Cấu trúc bảng cho bảng `typecar`
--

CREATE TABLE `typecar` (
  `Id` int(10) NOT NULL,
  `Type` varchar(50) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `typecar`
--

INSERT INTO `typecar` (`Id`, `Type`) VALUES
(1, 'Voiture'),
(2, 'Velo');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `typeuser`
--

CREATE TABLE `typeuser` (
  `Id` int(11) NOT NULL,
  `Profil` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `typeuser`
--

INSERT INTO `typeuser` (`Id`, `Profil`) VALUES
(1, 'manager'),
(2, 'ouvrier');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `urgencydegree`
--

CREATE TABLE `urgencydegree` (
  `Id` int(11) NOT NULL,
  `Description` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `urgencydegree`
--

INSERT INTO `urgencydegree` (`Id`, `Description`) VALUES
(1, 'Très urgent'),
(2, 'Urgent'),
(3, 'Normal'),
(4, 'Peu urgent'),
(5, 'Laisse tomber');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `users`
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
-- Đang đổ dữ liệu cho bảng `users`
--

INSERT INTO `users` (`Id`, `TypeUser`, `FirstName`, `LastName`, `DateOfBirth`, `Address`, `Town`, `PostalCode`, `Login`, `PasswordUser`, `Email`, `HiringDate`, `IncomingPerHour`) VALUES
(1, 2, 'test', 'test', '2017-04-11', 'azertyu', 'azertyu', 92100, 'test', 'test', 'test', '2017-04-03', 2),
(2, 1, 'john', 'doe', '2017-04-04', 'azerty', 'azerty', 94000, 'boss', 'boss', 'azrryt', '2017-06-12', 1);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `car`
--
ALTER TABLE `car`
  ADD PRIMARY KEY (`NumPuce`),
  ADD KEY `fk_typeV` (`TypeVehicule`) USING BTREE;

--
-- Chỉ mục cho bảng `carddefect`
--
ALTER TABLE `carddefect`
  ADD PRIMARY KEY (`IdDefect`,`IdCard`),
  ADD KEY `fk_Card_CardDefect` (`IdCard`);

--
-- Chỉ mục cho bảng `cardrepairs`
--
ALTER TABLE `cardrepairs`
  ADD PRIMARY KEY (`IdRepair`,`IdCard`),
  ADD KEY `fk_Card_CardRepair` (`IdCard`);

--
-- Chỉ mục cho bảng `cardstate`
--
ALTER TABLE `cardstate`
  ADD PRIMARY KEY (`Id`);

--
-- Chỉ mục cho bảng `defect`
--
ALTER TABLE `defect`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `fk_part` (`partForRepair`);

--
-- Chỉ mục cho bảng `orderpart`
--
ALTER TABLE `orderpart`
  ADD PRIMARY KEY (`IdPart`,`IdUser`,`Qte`,`date`),
  ADD KEY `fk_User_OrderPart` (`IdUser`);

--
-- Chỉ mục cho bảng `parking`
--
ALTER TABLE `parking`
  ADD PRIMARY KEY (`NumParking`);

--
-- Chỉ mục cho bảng `part`
--
ALTER TABLE `part`
  ADD PRIMARY KEY (`Id`);

--
-- Chỉ mục cho bảng `partdefect`
--
ALTER TABLE `partdefect`
  ADD PRIMARY KEY (`IdPart`,`IdDefect`),
  ADD KEY `fk_Defect_PartDefect` (`IdDefect`);

--
-- Chỉ mục cho bảng `partrepairs`
--
ALTER TABLE `partrepairs`
  ADD PRIMARY KEY (`IdPart`,`IdRepair`),
  ADD KEY `fk_Repair_PartRepairs` (`IdRepair`);

--
-- Chỉ mục cho bảng `place`
--
ALTER TABLE `place`
  ADD PRIMARY KEY (`NumPlace`),
  ADD KEY `fk_place` (`NumPark`);

--
-- Chỉ mục cho bảng `repaircard`
--
ALTER TABLE `repaircard`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `fk_Urgency_RepairCard` (`IdDegree`),
  ADD KEY `fk_CardState_RepairCard` (`IdCard`),
  ADD KEY `fk_Car_RepairCard` (`IdCar`),
  ADD KEY `fk_ParkPlace_RepairCard` (`IdParkPlace`),
  ADD KEY `fk_User_RepairCard` (`IdUser`);

--
-- Chỉ mục cho bảng `repairs`
--
ALTER TABLE `repairs`
  ADD PRIMARY KEY (`Id`);

--
-- Chỉ mục cho bảng `typecar`
--
ALTER TABLE `typecar`
  ADD PRIMARY KEY (`Id`);

--
-- Chỉ mục cho bảng `typeuser`
--
ALTER TABLE `typeuser`
  ADD PRIMARY KEY (`Id`);

--
-- Chỉ mục cho bảng `urgencydegree`
--
ALTER TABLE `urgencydegree`
  ADD PRIMARY KEY (`Id`);

--
-- Chỉ mục cho bảng `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `fk_Type` (`TypeUser`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `cardstate`
--
ALTER TABLE `cardstate`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT cho bảng `defect`
--
ALTER TABLE `defect`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;
--
-- AUTO_INCREMENT cho bảng `parking`
--
ALTER TABLE `parking`
  MODIFY `NumParking` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT cho bảng `part`
--
ALTER TABLE `part`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;
--
-- AUTO_INCREMENT cho bảng `repaircard`
--
ALTER TABLE `repaircard`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT cho bảng `repairs`
--
ALTER TABLE `repairs`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT cho bảng `typecar`
--
ALTER TABLE `typecar`
  MODIFY `Id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT cho bảng `typeuser`
--
ALTER TABLE `typeuser`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT cho bảng `urgencydegree`
--
ALTER TABLE `urgencydegree`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT cho bảng `users`
--
ALTER TABLE `users`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `carddefect`
--
ALTER TABLE `carddefect`
  ADD CONSTRAINT `fk_Card_CardDefect` FOREIGN KEY (`IdCard`) REFERENCES `repaircard` (`Id`),
  ADD CONSTRAINT `fk_Defect_CardDefect` FOREIGN KEY (`IdDefect`) REFERENCES `defect` (`Id`);

--
-- Các ràng buộc cho bảng `cardrepairs`
--
ALTER TABLE `cardrepairs`
  ADD CONSTRAINT `fk_Card_CardRepair` FOREIGN KEY (`IdCard`) REFERENCES `repaircard` (`Id`),
  ADD CONSTRAINT `fk_Repairs_CardRepair` FOREIGN KEY (`IdRepair`) REFERENCES `repairs` (`Id`);

--
-- Các ràng buộc cho bảng `defect`
--
ALTER TABLE `defect`
  ADD CONSTRAINT `fk_part` FOREIGN KEY (`partForRepair`) REFERENCES `part` (`Id`);

--
-- Các ràng buộc cho bảng `orderpart`
--
ALTER TABLE `orderpart`
  ADD CONSTRAINT `fk_Part_OrderPart` FOREIGN KEY (`IdPart`) REFERENCES `part` (`Id`),
  ADD CONSTRAINT `fk_User_OrderPart` FOREIGN KEY (`IdUser`) REFERENCES `users` (`Id`);

--
-- Các ràng buộc cho bảng `partdefect`
--
ALTER TABLE `partdefect`
  ADD CONSTRAINT `fk_Defect_PartDefect` FOREIGN KEY (`IdDefect`) REFERENCES `defect` (`Id`),
  ADD CONSTRAINT `fk_Part_PartDefect` FOREIGN KEY (`IdPart`) REFERENCES `part` (`Id`);

--
-- Các ràng buộc cho bảng `partrepairs`
--
ALTER TABLE `partrepairs`
  ADD CONSTRAINT `fk_Part_PartRepairs` FOREIGN KEY (`IdPart`) REFERENCES `part` (`Id`),
  ADD CONSTRAINT `fk_Repair_PartRepairs` FOREIGN KEY (`IdRepair`) REFERENCES `repairs` (`Id`);

--
-- Các ràng buộc cho bảng `place`
--
ALTER TABLE `place`
  ADD CONSTRAINT `fk_place` FOREIGN KEY (`NumPark`) REFERENCES `parking` (`NumParking`);

--
-- Các ràng buộc cho bảng `repaircard`
--
ALTER TABLE `repaircard`
  ADD CONSTRAINT `fk_Car_RepairCard` FOREIGN KEY (`IdCar`) REFERENCES `car` (`NumPuce`),
  ADD CONSTRAINT `fk_CardState_RepairCard` FOREIGN KEY (`IdCard`) REFERENCES `cardstate` (`Id`),
  ADD CONSTRAINT `fk_ParkPlace_RepairCard` FOREIGN KEY (`IdParkPlace`) REFERENCES `place` (`NumPlace`),
  ADD CONSTRAINT `fk_Urgency_RepairCard` FOREIGN KEY (`IdDegree`) REFERENCES `urgencydegree` (`Id`),
  ADD CONSTRAINT `fk_User_RepairCard` FOREIGN KEY (`IdUser`) REFERENCES `users` (`Id`);

--
-- Các ràng buộc cho bảng `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `fk_Type` FOREIGN KEY (`TypeUser`) REFERENCES `typeuser` (`Id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
