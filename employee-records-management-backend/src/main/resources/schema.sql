CREATE TABLE IF NOT EXISTS `users` (
    `id` INT AUTO_INCREMENT PRIMARY KEY,
    `username` VARCHAR(45) NOT NULL,
    `password` VARCHAR(255) NOT NULL,
    `email` VARCHAR(100) NOT NULL UNIQUE,
    `enabled` BOOLEAN NOT NULL DEFAULT TRUE,
    `expiration_date` DATETIME,
    `password_expiration_date` DATETIME,
    `is_temporary_password` BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE IF NOT EXISTS `roles` (
    `id` INT AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(45) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS `privileges` (
    `id` INT AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(45) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS `users_roles` (
    `user_id` INT NOT NULL,
    `role_id` INT NOT NULL,
    PRIMARY KEY (`user_id`, `role_id`),
    FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE,
    FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS `roles_privileges` (
    `role_id` INT NOT NULL,
    `privilege_id` INT NOT NULL,
    PRIMARY KEY (`role_id`, `privilege_id`),
    FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`) ON DELETE CASCADE,
    FOREIGN KEY (`privilege_id`) REFERENCES `privileges` (`id`) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS `department` (
     `id` INT AUTO_INCREMENT PRIMARY KEY,
     `name` VARCHAR(255) NOT NULL,
     `created_at` DATE NOT NULL,
     `created_by` VARCHAR(20) NOT NULL,
     `updated_at` DATE DEFAULT NULL,
     `updated_by` VARCHAR(20) DEFAULT NULL
);
CREATE TABLE IF NOT EXISTS `employee` (
      `id` INT AUTO_INCREMENT PRIMARY KEY,
      `employee_id` VARCHAR(50) UNIQUE NOT NULL,
      `full_name` VARCHAR(255) NOT NULL,
      `job_title` VARCHAR(255) DEFAULT NULL,
      `department_id` INT NOT NULL,
      `hire_date` DATE NOT NULL,
      `employment_status` VARCHAR(20) DEFAULT 'ACTIVE',
      `email` VARCHAR(255) UNIQUE NOT NULL,
      `phone_number` VARCHAR(20) DEFAULT NULL,
      `address` TEXT DEFAULT NULL,
      `created_at` DATE NOT NULL,
      `created_by` VARCHAR(20) NOT NULL,
      `updated_at` DATE DEFAULT NULL,
      `updated_by` VARCHAR(20) DEFAULT NULL,
      FOREIGN KEY (`department_id`) REFERENCES `department`(`id`) ON DELETE CASCADE
);


-- Insert predefined roles
INSERT INTO `roles` (`name`) VALUES ('ADMIN'), ('HR'), ('MANAGER'), ('USER');

-- Insert predefined privileges
INSERT INTO `privileges` (`name`) VALUES ('READ'), ('CREATE'), ('UPDATE'), ('LIMITED_UPDATE'), ('DELETE');

-- Assign privileges to roles
INSERT INTO `roles_privileges` (`role_id`, `privilege_id`) VALUES (1, 1), (1, 2), (1, 3), (1, 4), (1, 5); -- ADMIN has all privileges
INSERT INTO `roles_privileges` (`role_id`, `privilege_id`) VALUES  (2, 1), (2, 2), (2, 3), (2, 4), (2, 5); -- HR has all privileges
INSERT INTO `roles_privileges` (`role_id`, `privilege_id`) VALUES (3, 1), (3, 4); -- MANAGER has READ and LIMITED_UPDATE Privileges

-- Insert sample users
INSERT INTO `users` (`username`, `password`, `email`, `enabled`, `expiration_date`, `password_expiration_date`, `is_temporary_password`) VALUES
     ('admin', '$2a$10$IqTJTjn39IU5.7sSCDQxzu3xug6z/LPU6IF0azE/8CkHCwYEnwBX.', 'admin@company.com', 1, '2025-12-31 23:59:59', '2024-06-30 23:59:59', 0),
     ('sara', '$2a$12$TNieojfQlbRC9FepldmCU.3KXmxeaiV2n2F0l73eY8TVMVcUdLd9e', 'sara@company.com', 1, '2025-12-31 23:59:59', '2024-06-30 23:59:59', 0),
     ('yassin', '$2a$12$V.K0IN1v/duWjvKHrHn.peeH4rwkl0yspxdGyrqIqGbx5vAWt6NzG', 'yassin@company.com', 1, '2025-12-31 23:59:59', '2024-06-30 23:59:59', 0);
-- Assign roles to users
INSERT INTO `users_roles` (`user_id`, `role_id`) VALUES (1, 1); -- admin is ADMIN
INSERT INTO `users_roles` (`user_id`, `role_id`) VALUES (1, 2); -- admin is HR
INSERT INTO `users_roles` (`user_id`, `role_id`) VALUES (1, 3); -- admin is MANAGER
INSERT INTO `users_roles` (`user_id`, `role_id`) VALUES (1, 4); -- admin is USER
INSERT INTO `users_roles` (`user_id`, `role_id`) VALUES (2, 2); -- sara is HR
INSERT INTO `users_roles` (`user_id`, `role_id`) VALUES (3, 3); -- yassin is MANAGER

INSERT INTO `department` (`name`, `created_at`, `created_by`, `updated_at`, `updated_by`)
VALUES
    ('Human Resources', '2024-01-01', 'System', NULL, NULL),
    ('Finance', '2024-01-02', 'System', NULL, NULL),
    ('IT', '2024-01-03', 'System', NULL, NULL),
    ('Marketing', '2024-01-04', 'System', NULL, NULL),
    ('Operations', '2024-01-05', 'System', NULL, NULL);

INSERT INTO `employee` (`employee_id`, `full_name`, `job_title`, `department_id`, `hire_date`, `employment_status`, `email`, `phone_number`, `address`, `created_at`, `created_by`, `updated_at`, `updated_by`)
VALUES
    ('f85d0d5e-10b6-4b9a-a9b2-0d2f2db9f088', 'John Doe', 'HR Manager', 1, '2024-01-10', 'ACTIVE', 'johndoe@example.com', '1234567890', '123 HR Street', '2024-01-10', 'System', NULL, NULL),
    ('7a6b4a4e-4b29-4f5c-b3e3-22b619a43b99', 'Jane Smith', 'Accountant', 2, '2024-01-12', 'ACTIVE', 'janesmith@example.com', '2345678901', '456 Finance Avenue', '2024-01-12', 'System', NULL, NULL),
    ('e08f4f63-6886-4871-975d-7d2c61a7b79d', 'Emily Johnson', 'IT Specialist', 3, '2024-01-15', 'ACTIVE', 'emilyjohnson@example.com', '3456789012', '789 IT Boulevard', '2024-01-15', 'System', NULL, NULL),
    ('1c7f8c0b-1e0d-4b80-b79e-0c97a9307ff9', 'Michael Brown', 'Marketing Manager', 4, '2024-01-20', 'ACTIVE', 'michaelbrown@example.com', '4567890123', '101 Marketing Road', '2024-01-20', 'System', NULL, NULL),
    ('d960ec6b-9f84-41fe-9ad3-8fd8fdd73b59', 'Sarah Lee', 'Operations Coordinator', 5, '2024-01-22', 'ACTIVE', 'sarahlee@example.com', '5678901234', '202 Operations Drive', '2024-01-22', 'System', NULL, NULL),
    ('c97a97eb-cbaf-4d06-a9d2-f73013d1b65a', 'David Wilson', 'HR Assistant', 1, '2024-01-25', 'ACTIVE', 'davidwilson@example.com', '6789012345', '303 HR Plaza', '2024-01-25', 'System', NULL, NULL),
    ('c35c4a2c-3565-4d61-b8e6-fbe9f728a86d', 'Laura Miller', 'Finance Analyst', 2, '2024-01-28', 'ACTIVE', 'lauramiller@example.com', '7890123456', '404 Finance Street', '2024-01-28', 'System', NULL, NULL),
    ('ce25552e-9f9e-47db-a55b-101ba7789fe7', 'James Taylor', 'IT Support', 3, '2024-02-01', 'ACTIVE', 'jamestaylor@example.com', '8901234567', '505 IT Square', '2024-02-01', 'System', NULL, NULL),
    ('3e72d28e-6e0d-487f-88d7-dba85bc1c780', 'Patricia Anderson', 'Marketing Specialist', 4, '2024-02-05', 'ACTIVE', 'patriciaanderson@example.com', '9012345678', '606 Marketing Circle', '2024-02-05', 'System', NULL, NULL),
    ('3c43ecbf-8e5a-4668-b68e-1b8cfd967f4c', 'Robert Thomas', 'Operations Manager', 5, '2024-02-08', 'ACTIVE', 'robertthomas@example.com', '0123456789', '707 Operations Lane', '2024-02-08', 'System', NULL, NULL);
