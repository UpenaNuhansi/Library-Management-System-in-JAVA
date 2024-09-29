show databases;
create database library_db;
use library_db;

CREATE TABLE books (
    book_id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    publisher VARCHAR(255),
    year_published INT
);


CREATE TABLE members (
    member_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    phone VARCHAR(20)
);


CREATE TABLE loans (
    loan_id INT PRIMARY KEY AUTO_INCREMENT,
    book_id INT,
    member_id INT,
    loan_date DATE,
    return_date DATE,
    FOREIGN KEY (book_id) REFERENCES books(book_id),
    FOREIGN KEY (member_id) REFERENCES members(member_id)
);

ALTER TABLE loans ADD due_date DATE;

INSERT INTO books (book_id, title, author, publisher, year_published)
VALUES 
  (1,'To Kill a Mockingbird', 'Harper Lee', 'Lippincott', 1960),
  (2,'The Lord of the Rings', 'J. R. R. Tolkien', 'Allen & Unwin', 1954),
  (3,'Pride and Prejudice', 'Jane Austen', 'E. P. Dutton and Co.', 1813);

INSERT INTO members (member_id, name, email, phone)
VALUES 
  (1, 'John Smith', 'johnsmith@gmail.com', '0812348906'),
  (2, 'Jane Doe', 'janedoe@gmail.com', '0712344563'),
  (3, 'Michael Brown', 'michaelbrown@gmail.com', '0778901234');

INSERT INTO loans (book_id, member_id, loan_date, return_date)
VALUES 
  (1, 1, '2024-06-28', NULL),
  (2, 2, '2024-06-25', '2024-07-09'),
  (3, 3, '2024-06-20', '2024-07-03');

ALTER TABLE books ADD availability BOOLEAN DEFAULT TRUE;

show tables;
show table status;
select * from books;
select * from loans;
select * from members;
