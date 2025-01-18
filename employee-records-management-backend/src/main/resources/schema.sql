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



INSERT INTO `department` (`name`, `created_at`, `created_by`, `updated_at`, `updated_by`)
VALUES
    ('Human Resources', '2025-01-01', 'System', NULL, NULL),
    ('Finance', '2025-01-02', 'System', NULL, NULL),
    ('IT', '2025-01-03', 'System', NULL, NULL),
    ('Marketing', '2025-01-04', 'System', NULL, NULL),
    ('Operations', '2025-01-05', 'System', NULL, NULL);

INSERT INTO `employee` (`employee_id`, `full_name`, `job_title`, `department_id`, `hire_date`, `employment_status`, `email`, `phone_number`, `address`, `created_at`, `created_by`, `updated_at`, `updated_by`)
VALUES
    ('f85d0d5e-10b6-4b9a-a9b2-0d2f2db9f088', 'John Doe', 'HR Manager', 1, '2025-01-10', 'ACTIVE', 'johndoe@example.com', '123-456-7890', '123 HR Street', '2025-01-10', 'System', NULL, NULL),
    ('7a6b4a4e-4b29-4f5c-b3e3-22b619a43b99', 'Jane Smith', 'Accountant', 2, '2025-01-12', 'ACTIVE', 'janesmith@example.com', '234-567-8901', '456 Finance Avenue', '2025-01-12', 'System', NULL, NULL),
    ('e08f4f63-6886-4871-975d-7d2c61a7b79d', 'Emily Johnson', 'IT Specialist', 3, '2025-01-15', 'ACTIVE', 'emilyjohnson@example.com', '345-678-9012', '789 IT Boulevard', '2025-01-15', 'System', NULL, NULL),
    ('1c7f8c0b-1e0d-4b80-b79e-0c97a9307ff9', 'Michael Brown', 'Marketing Manager', 4, '2025-01-20', 'ACTIVE', 'michaelbrown@example.com', '456-789-0123', '101 Marketing Road', '2025-01-20', 'System', NULL, NULL),
    ('d960ec6b-9f84-41fe-9ad3-8fd8fdd73b59', 'Sarah Lee', 'Operations Coordinator', 5, '2025-01-22', 'ACTIVE', 'sarahlee@example.com', '567-890-1234', '202 Operations Drive', '2025-01-22', 'System', NULL, NULL),
    ('c97a97eb-cbaf-4d06-a9d2-f73013d1b65a', 'David Wilson', 'HR Assistant', 1, '2025-01-25', 'ACTIVE', 'davidwilson@example.com', '678-901-2345', '303 HR Plaza', '2025-01-25', 'System', NULL, NULL),
    ('c35c4a2c-3565-4d61-b8e6-fbe9f728a86d', 'Laura Miller', 'Finance Analyst', 2, '2025-01-28', 'ACTIVE', 'lauramiller@example.com', '789-012-3456', '404 Finance Street', '2025-01-28', 'System', NULL, NULL),
    ('ce25552e-9f9e-47db-a55b-101ba7789fe7', 'James Taylor', 'IT Support', 3, '2025-02-01', 'ACTIVE', 'jamestaylor@example.com', '890-123-4567', '505 IT Square', '2025-02-01', 'System', NULL, NULL),
    ('3e72d28e-6e0d-487f-88d7-dba85bc1c780', 'Patricia Anderson', 'Marketing Specialist', 4, '2025-02-05', 'ACTIVE', 'patriciaanderson@example.com', '901-234-5678', '606 Marketing Circle', '2025-02-05', 'System', NULL, NULL),
    ('3c43ecbf-8e5a-4668-b68e-1b8cfd967f4c', 'Robert Thomas', 'Operations Manager', 5, '2025-02-08', 'ACTIVE', 'robertthomas@example.com', '012-345-6789', '707 Operations Lane', '2025-02-08', 'System', NULL, NULL);
