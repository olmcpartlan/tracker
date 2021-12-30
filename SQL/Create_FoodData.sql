CREATE TABLE `FoodData` (
                            `relationId` varchar(100) NOT NULL,
                            `foodId` varchar(100) NOT NULL,
                            `userId` varchar(100) NOT NULL,
                            `quantity` double NOT NULL,
                            `createdAt` datetime NOT NULL,
                            `updatedAt` datetime NOT NULL,
                            PRIMARY KEY (`relationId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
