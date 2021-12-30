CREATE TABLE `users` (
                         `user_id` varchar(300) NOT NULL,
                         `user_name` varchar(100) NOT NULL,
                         `user_email` varchar(100) NOT NULL,
                         `user_pass` varchar(100) NOT NULL,
                         `created_at` datetime NOT NULL,
                         `updated_at` datetime NOT NULL,
                         PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
