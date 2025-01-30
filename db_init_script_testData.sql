
CREATE TABLE userhub.user_data (
                                   user_id int8 NOT NULL,
                                   first_name varchar(30) NULL,
                                   last_name varchar(30) NULL,
                                   "password" text NOT NULL,
                                   username varchar(255) NOT NULL,
                                   CONSTRAINT uk_nlc4atex50p892vsfhwccm336 UNIQUE (username),
                                   CONSTRAINT user_data_pkey PRIMARY KEY (user_id)
);

CREATE TABLE userhub.contact_data (
                                      contact_id int8 NOT NULL,
                                      contact_type varchar(255) NULL,
                                      email varchar(255) NULL,
                                      mobile_number1 varchar(12) NULL,
                                      mobile_number2 varchar(12) NULL,
                                      user_id int8 NULL,
                                      CONSTRAINT contact_data_pkey PRIMARY KEY (contact_id)
);
-- userhub.contact_data foreign keys

ALTER TABLE userhub.contact_data ADD CONSTRAINT fknrpba09qdopmrta73pm4sh4u5 FOREIGN KEY (user_id) REFERENCES userhub.user_data(user_id);


-- userhub.address_data table creation

CREATE TABLE userhub.address_data (
                                      address_id int8 NOT NULL,
                                      address_line1 varchar(100) NULL,
                                      address_line2 varchar(100) NULL,
                                      address_type varchar(255) NULL,
                                      city varchar(100) NULL,
                                      country varchar(100) NULL,
                                      pincode int8 NULL,
                                      state varchar(100) NULL,
                                      user_id int8 NULL,
                                      CONSTRAINT address_data_pkey PRIMARY KEY (address_id)
);

-- userhub.address_data foreign keys

ALTER TABLE userhub.address_data ADD CONSTRAINT fkbg31vxwpxtq5rok8tevex9qn1 FOREIGN KEY (user_id) REFERENCES userhub.user_data(user_id);

--adding dummy data for testing

--Insert User Data
INSERT INTO userhub.user_data
(user_id, first_name, last_name, "password", username)
VALUES
(10000001, 'sathish kumar', 'K', '123', 'frost'),
(10000002, 'aishwarya', 'M', '123', 'aishu'),
(10000003, 'Karmukilan', 'MR', '123', 'kar');

-- Insert Contact Data
INSERT INTO userhub.contact_data
(contact_id, contact_type, email, mobile_number1, mobile_number2, user_id)
VALUES
(20000001, 'Home1', 'forst@gmail.com', '11111111', '22222', 10000001),
(20000002, 'Home2', 'forst2@gmail.com', '333333', '444444', 10000001),
(20000003, 'Home1', 'aishu@gmail.com', '5555555', '66666', 10000002),
(20000004, 'Home2', 'aishu2@gmail.com', '777777', '8888888', 10000002),
(20000005, 'Home1', 'kar@gmail.com', '99999', '1010101010', 10000003),
(20000006, 'Home2', 'kar2@gmail.com', '011011011', '012012012012',10000003);


-- Insert Address Data

INSERT INTO userhub.address_data
(address_id, address_line1, address_line2, address_type, city, country, pincode, state, user_id)
VALUES
(20000001, 'street 11', 'corner 21', 'Home 11', 'chennai1', 'India1', 6416551, 'Tamil Nadu1', 10000001),
(20000002, 'street 12', 'corner 22', 'Home 12', 'chennai2', 'India2', 6416552, 'Tamil Nadu2', 10000001),
(20000003, 'street 13', 'corner 23', 'Home 13', 'chennai3', 'India3', 6416553, 'Tamil Nadu3', 10000002),
(20000004, 'street 14', 'corner 24', 'Home 14', 'chennai4', 'India4', 6416554, 'Tamil Nadu4', 10000002),
(20000005, 'street 15', 'corner 25', 'Home 15', 'chennai5', 'India5', 6416555, 'Tamil Nadu5', 10000003),
(20000006, 'street 16', 'corner 26', 'Home 16', 'chennai6', 'India6', 6416556, 'Tamil Nadu6', 10000003);
