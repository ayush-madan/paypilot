CREATE TABLE Bills (
    bill_id NUMBER PRIMARY KEY,  -- int in Java
    bill_name VARCHAR2(100),     -- String in Java
    bill_category VARCHAR2(100), -- String in Java
    due_date DATE,               -- java.util.Date in Java
    amount NUMBER(10, 2),        -- double in Java
    reminder_frequency VARCHAR2(50), -- String in Java
    attachment BLOB,             -- java.io.File (binary data) in Java
    notes VARCHAR2(4000),        -- String in Java
    is_recurring VARCHAR2(10),   -- String in Java (Assuming yes/no or true/false)
    payment_status VARCHAR2(50), -- String in Java
    overdue_days NUMBER,         -- int in Java
    user_id NUMBER               -- int in Java
);
CREATE SEQUENCE bill_id_seq START WITH 1 INCREMENT BY 1; --incrementing the id of bill if not entered

-- Insert Dummy Data
INSERT INTO Bills (bill_id, bill_name, bill_category, due_date, amount, reminder_frequency, attachment, notes, is_recurring, payment_status, overdue_days, user_id)
VALUES (1, 'Electricity Bill', 'Utilities', TO_DATE('2023-09-15', 'YYYY-MM-DD'), 120.50, 'Monthly', null , 'Pay before 20th', 'Yes', 'Pending', 5, 101);

INSERT INTO Bills (bill_id, bill_name, bill_category, due_date, amount, reminder_frequency, attachment, notes, is_recurring, payment_status, overdue_days, user_id)
VALUES (2, 'Water Bill', 'Utilities', TO_DATE('2023-09-20', 'YYYY-MM-DD'), 80.75, 'Monthly', null , 'Due soon', 'No', 'Paid', 0, 102);

INSERT INTO Bills (bill_id, bill_name, bill_category, due_date, amount, reminder_frequency, attachment, notes, is_recurring, payment_status, overdue_days, user_id)
VALUES (3, 'Internet Bill', 'Communications', TO_DATE('2023-09-10', 'YYYY-MM-DD'), 45.99, 'Monthly', null, 'Autopay activated', 'Yes', 'Pending', 2, 103);

CREATE TABLE ReminderSettings (
    reminder_id NUMBER PRIMARY KEY,  -- int in Java
    bill_id NUMBER,                  -- int in Java (Foreign Key to Bills table)
    reminder_time DATE,              -- java.util.Date in Java
    message VARCHAR2(500),           -- String in Java
    frequency VARCHAR2(50),          -- String in Java
    active VARCHAR2(10),             -- String in Java (Assumed yes/no or true/false)
    CONSTRAINT fk_bill FOREIGN KEY (bill_id) REFERENCES Bills(bill_id) ON DELETE CASCADE
);

-- Insert Dummy Data
INSERT INTO ReminderSettings (reminder_id, bill_id, reminder_time, message, frequency, active)
VALUES (1, 1, TO_DATE('2023-09-13 18:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'Electricity Bill Reminder', 'Monthly', 'Yes');

INSERT INTO ReminderSettings (reminder_id, bill_id, reminder_time, message, frequency, active)
VALUES (2, 2, TO_DATE('2023-09-18 18:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'Water Bill Reminder', 'Monthly', 'Yes');

INSERT INTO ReminderSettings (reminder_id, bill_id, reminder_time, message, frequency, active)
VALUES (3, 3, TO_DATE('2023-09-08 18:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'Internet Bill Reminder', 'Monthly', 'No');
