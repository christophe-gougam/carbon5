create table Car(
  NumPuce varchar(100) PRIMARY KEY,
  TypeVehicule varchar(200) DEFAULT NULL

);

create table Parking(
  NumParking int Primary Key auto_increment,
  NomParking varchar(50),
  Capacity int
);

create table Place(
  NumPlace int Primary Key,
  NumPark int,
  IsOccupied boolean,
  IdParking int
);

ALTER TABLE Place ADD CONSTRAINT fk_place FOREIGN KEY (IdParking) REFERENCES Parking(NumParking);

create table CardState(
  Id int Primary Key AUTO_INCREMENT,
  Description varchar(50)
);

create table UrgencyDegree(
  Id int Primary Key AUTO_INCREMENT,
  Description varchar(50)
);

create table Part(
  Id int Primary Key AUTO_INCREMENT,
  Stock int,
  NamePart varchar(50),
  PurchasePrice float
);

create table Users(
  Id int Primary Key AUTO_INCREMENT,
  TypeUser int,
  FirstName varchar(50),
  LastName varchar (50),
  DateOfBirth date,
  Address varchar (50),
  Town varchar (50),
  PostalCode int,
  Login varchar (50),
  PasswordUser varchar (50),
  Email varchar (50),
  HiringDate date,
  IncomingPerHour float
);

create table TypeUser(
  Id int Primary Key AUTO_INCREMENT,
  Profil varchar(50)
);

create table RepairCard(
  Id int Primary Key AUTO_INCREMENT,
  IdDegree int,
  IdCard int,
  IdCar varchar(100),
  IdParkPlace int,
  EntryDate date,
  OutDate date,
  OverAllDetails text,
  IdUser int
);

create table Repairs(
  Id int Primary Key AUTO_INCREMENT,
  DateRepair date,
  Nature varchar(50),
  TimeSpent float,
  Description text
);

create table Defect(
  Id int Primary Key AUTO_INCREMENT,
  Description text
);

create table CardDefect(
  IdDefect int,
  IdCard int
);

ALTER TABLE CardDefect ADD PRIMARY KEY (IdDefect,IdCard);
ALTER TABLE CardDefect ADD CONSTRAINT fk_Card_CardDefect FOREIGN KEY (IdCard) REFERENCES RepairCard(Id);
ALTER TABLE CardDefect ADD CONSTRAINT fk_Defect_CardDefect FOREIGN KEY (IdDefect) REFERENCES Defect(Id);

create table CardRepairs(
  IdRepair int,
  IdCard int
);

ALTER TABLE CardRepairs ADD PRIMARY KEY (IdRepair,IdCard);
ALTER TABLE CardRepairs ADD CONSTRAINT fk_Repairs_CardRepair FOREIGN KEY (IdRepair) REFERENCES Repairs(Id);
ALTER TABLE CardRepairs ADD CONSTRAINT fk_Card_CardRepair FOREIGN KEY (IdCard) REFERENCES RepairCard(Id);

create table PartDefect(
  IdPart int,
  IdDefect int
);

ALTER TABLE PartDefect ADD PRIMARY KEY (IdPart,IdDefect);
ALTER TABLE PartDefect ADD CONSTRAINT fk_Part_PartDefect FOREIGN KEY (IdPart) REFERENCES Part(Id);
ALTER TABLE PartDefect ADD CONSTRAINT fk_Defect_PartDefect FOREIGN KEY (IdDefect) REFERENCES Defect(Id);

create table PartRepairs(
  IdPart int,
  IdRepair int
);

ALTER TABLE PartRepairs ADD PRIMARY KEY (IdPart,IdRepair);
ALTER TABLE PartRepairs ADD CONSTRAINT fk_Part_PartRepairs FOREIGN KEY (IdPart) REFERENCES Part(Id);
ALTER TABLE PartRepairs ADD CONSTRAINT fk_Repair_PartRepairs FOREIGN KEY (IdRepair) REFERENCES Repairs(Id);

ALTER TABLE Users ADD CONSTRAINT fk_Type FOREIGN KEY (TypeUser) REFERENCES TypeUser(Id);

ALTER TABLE RepairCard ADD CONSTRAINT fk_Urgency_RepairCard FOREIGN KEY (IdDegree) REFERENCES UrgencyDegree(Id);
ALTER TABLE RepairCard ADD CONSTRAINT fk_CardState_RepairCard FOREIGN KEY (IdCard) REFERENCES CardState(Id);
ALTER TABLE RepairCard ADD CONSTRAINT fk_Car_RepairCard FOREIGN KEY (IdCar) REFERENCES Car(NumPuce);
ALTER TABLE RepairCard ADD CONSTRAINT fk_ParkPlace_RepairCard FOREIGN KEY (IdParkPlace) REFERENCES Place(NumPlace);
ALTER TABLE RepairCard ADD CONSTRAINT fk_User_RepairCard FOREIGN KEY (IdUser) REFERENCES Users(Id);

create table OrderPart(
  IdPart int,
  IdUser int,
  Qte int
);

ALTER TABLE OrderPart ADD CONSTRAINT fk_Part_OrderPart FOREIGN KEY (IdPart) REFERENCES Part(Id);
ALTER TABLE OrderPart ADD CONSTRAINT fk_User_OrderPart FOREIGN KEY (IdUser) REFERENCES Users(Id);


