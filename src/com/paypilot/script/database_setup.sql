CREATE TABLE Bills (
    bill_id VARCHAR2(50) PRIMARY KEY,
    bill_name VARCHAR2(100),
    bill_category VARCHAR2(50),
    due_date DATE,
    amount FLOAT,
    reminder_frequency VARCHAR2(20),
    attachment VARCHAR2(255),
    notes VARCHAR2(255),
    is_recurring BOOLEAN,
    payment_status VARCHAR2(20),
    overdue_days INT,
    user_id VARCHAR2(50)
);

CREATE TABLE ReminderSettings (
    reminder_id VARCHAR2(50) PRIMARY KEY,
    reminder_frequency VARCHAR2(20),
    reminder_start_date DATE,
    custom_message VARCHAR2(255),
    notification_preference VARCHAR2(20),
    bill_id VARCHAR2(50),
    FOREIGN KEY (bill_id) REFERENCES Bills(bill_id)
);

INSERT INTO Bills (bill_id, bill_name, bill_category, due_date, amount, reminder_frequency, attachment, notes, is_recurring, payment_status, overdue_days, user_id)
VALUES
('B001', 'Electricity Bill', 'Utility', TO_DATE('2024-07-15', 'YYYY-MM-DD'), 120.50, 'monthly', 'path/to/attachment1.pdf', 'Electricity bill for July', TRUE, 'unpaid', 0, 'U001'),
('B002', 'Credit Card Payment', 'Credit Card', TO_DATE('2024-07-20', 'YYYY-MM-DD'), 450.00, 'monthly', 'path/to/attachment2.pdf', 'Credit card payment for July', TRUE, 'paid', 5, 'U002'),
('B003', 'Subscription Fee', 'Subscription', TO_DATE('2024-07-25', 'YYYY-MM-DD'), 75.00, 'monthly', NULL, 'Subscription fee for July', FALSE, 'pending', 0, 'U001');


INSERT INTO ReminderSettings (reminder_id, reminder_frequency, reminder_start_date, custom_message, notification_preference, bill_id)
VALUES
('R001', 'weekly', TO_DATE('2024-07-01', 'YYYY-MM-DD'), 'Reminder: Electricity Bill due soon', 'email', 'B001'),
('R002', 'monthly', TO_DATE('2024-07-01', 'YYYY-MM-DD'), 'Reminder: Credit Card Payment due', 'SMS', 'B002'),
('R003', 'monthly', TO_DATE('2024-07-01', 'YYYY-MM-DD'), 'Reminder: Subscription Fee due', 'push notification', 'B003');
