-- Insert data into the Customer table
INSERT INTO Customer (id, name, surname, credit_Limit, used_Credit_Limit)
VALUES
(1, 'John', 'Doe', 5000.00, 500.00),
(2, 'Jane', 'Smith', 12000.00, 9000.00),
(3, 'Michael', 'Johnson', 10000.00, 3500.00),
(4, 'Emily', 'Davis', 6000.00, 0.00),
(5, 'William', 'Brown', 12000.00, 8800.00);


-- Insert data into the Loan table
INSERT INTO Loan (id, customer_Id, loan_Amount, number_Of_Installment, create_Date, is_Paid)
VALUES
(1, 1, 3000.00, 6, '2024-01-01', FALSE), -- Loan for John Doe
(2, 2, 5000.00, 12, '2024-02-15', FALSE), -- Loan for Jane Smith
(3, 3, 8000.00, 24, '2024-03-10', FALSE), -- Loan for Michael Johnson
(4, 4, 2500.00, 9, '2024-04-05', TRUE), -- Loan for Emily Davis (paid)
(5, 5, 10000.00, 12, '2024-05-20', FALSE); -- Loan for William Brown


-- Insert data into the Loan_Installment table

-- Installments for Loan 1 (loan_Id = 1, 6 installments)
INSERT INTO Loan_Installment (id, loan_Id, amount, paid_Amount, due_Date, payment_Date, is_Paid)
VALUES
(1, 1, 500.00, 500.00, '2024-02-01', '2024-02-01', TRUE),
(2, 1, 500.00, 500.00, '2024-03-01', '2024-03-01', TRUE),
(3, 1, 500.00, 500.00, '2024-04-01', '2024-04-01', TRUE),
(4, 1, 500.00, 500.00, '2024-05-01', '2024-05-01', TRUE),
(5, 1, 500.00, 500.00, '2024-06-01', '2024-06-01', TRUE),
(6, 1, 500.00, 0.00, '2024-07-01', NULL, FALSE);

-- Installments for Loan 2 (loan_Id = 2, 12 installments)
INSERT INTO Loan_Installment (id, loan_Id, amount, paid_Amount, due_Date, payment_Date, is_Paid)
VALUES
(7, 2, 416.67, 1000.00, '2024-03-15', '2024-03-15', TRUE),
(8, 2, 416.67, 1000.00, '2024-04-15', '2024-04-15', TRUE),
(9, 2, 416.67, 1000.00, '2024-05-15', '2024-05-15', TRUE),
(10, 2, 416.67, 0.00, '2024-06-15', NULL, FALSE),
(11, 2, 416.67, 0.00, '2024-07-15', NULL, FALSE),
(12, 2, 416.67, 0.00, '2024-08-15', NULL, FALSE),
(13, 2, 416.67, 0.00, '2024-09-15', NULL, FALSE),
(14, 2, 416.67, 0.00, '2024-10-15', NULL, FALSE),
(15, 2, 416.67, 0.00, '2024-11-15', NULL, FALSE),
(16, 2, 416.67, 0.00, '2024-12-15', NULL, FALSE),
(17, 2, 416.67, 0.00, '2025-01-15', NULL, FALSE),
(18, 2, 416.67, 0.00, '2025-02-15', NULL, FALSE);

-- Installments for Loan 3 (loan_Id = 3, 24 installments, fully completed)
INSERT INTO Loan_Installment (id, loan_Id, amount, paid_Amount, due_Date, payment_Date, is_Paid)
VALUES
(19, 3, 333.33, 250.00, '2024-04-10', '2024-04-10', TRUE),
(20, 3, 333.33, 250.00, '2024-05-10', '2024-05-10', TRUE),
(21, 3, 333.33, 0.00, '2024-06-10', NULL, FALSE),
(22, 3, 333.33, 0.00, '2024-07-10', NULL, FALSE),
(23, 3, 333.33, 0.00, '2024-08-10', NULL, FALSE),
(24, 3, 333.33, 0.00, '2024-09-10', NULL, FALSE),
(25, 3, 333.33, 0.00, '2024-10-10', NULL, FALSE),
(26, 3, 333.33, 0.00, '2024-11-10', NULL, FALSE),
(27, 3, 333.33, 0.00, '2024-12-10', NULL, FALSE),
(28, 3, 333.33, 0.00, '2025-01-10', NULL, FALSE),
(29, 3, 333.33, 0.00, '2025-02-10', NULL, FALSE),
(30, 3, 333.33, 0.00, '2025-03-10', NULL, FALSE),
(31, 3, 333.33, 0.00, '2025-04-10', NULL, FALSE),
(32, 3, 333.33, 0.00, '2025-05-10', NULL, FALSE),
(33, 3, 333.33, 0.00, '2025-06-10', NULL, FALSE),
(34, 3, 333.33, 0.00, '2025-07-10', NULL, FALSE),
(35, 3, 333.33, 0.00, '2025-08-10', NULL, FALSE),
(36, 3, 333.33, 0.00, '2025-09-10', NULL, FALSE),
(37, 3, 333.33, 0.00, '2025-10-10', NULL, FALSE),
(38, 3, 333.33, 0.00, '2025-11-10', NULL, FALSE),
(39, 3, 333.33, 0.00, '2025-12-10', NULL, FALSE),
(40, 3, 333.33, 0.00, '2026-01-10', NULL, FALSE),
(41, 3, 333.33, 0.00, '2026-02-10', NULL, FALSE),
(42, 3, 333.33, 0.00, '2026-03-10', NULL, FALSE);

-- Installments for Loan 4 (loan_Id = 4, 9 installments, already paid)
INSERT INTO Loan_Installment (id, loan_Id, amount, paid_Amount, due_Date, payment_Date, is_Paid)
VALUES
(43, 4, 300.00, 300.00, '2024-05-05', '2024-05-05', TRUE),
(44, 4, 300.00, 300.00, '2024-06-05', '2024-06-05', TRUE),
(45, 4, 300.00, 300.00, '2024-07-05', '2024-07-05', TRUE),
(46, 4, 300.00, 300.00, '2024-08-05', '2024-08-05', TRUE),
(47, 4, 300.00, 300.00, '2024-09-05', '2024-09-05', TRUE),
(48, 4, 300.00, 300.00, '2024-10-05', '2024-10-05', TRUE),
(49, 4, 300.00, 300.00, '2024-11-05', '2024-11-05', TRUE),
(50, 4, 300.00, 300.00, '2024-12-05', '2024-12-05', TRUE),
(51, 4, 300.00, 300.00, '2025-01-05', '2025-01-05', TRUE);

-- Installments for Loan 5 (loan_Id = 5, 12 installments)
INSERT INTO Loan_Installment (id, loan_Id, amount, paid_Amount, due_Date, payment_Date, is_Paid)
VALUES
(52, 5, 800.00, 800.00, '2024-06-20', '2024-06-20', TRUE),
(53, 5, 800.00, 0.00, '2024-07-20', NULL, FALSE),
(54, 5, 800.00, 0.00, '2024-08-20', NULL, FALSE),
(55, 5, 800.00, 0.00, '2024-09-20', NULL, FALSE),
(56, 5, 800.00, 0.00, '2024-10-20', NULL, FALSE),
(57, 5, 800.00, 0.00, '2024-11-20', NULL, FALSE),
(58, 5, 800.00, 0.00, '2024-12-20', NULL, FALSE),
(59, 5, 800.00, 0.00, '2025-01-20', NULL, FALSE),
(60, 5, 800.00, 0.00, '2025-02-20', NULL, FALSE),
(61, 5, 800.00, 0.00, '2025-03-20', NULL, FALSE),
(62, 5, 800.00, 0.00, '2025-04-20', NULL, FALSE),
(63, 5, 800.00, 0.00, '2025-05-20', NULL, FALSE);
