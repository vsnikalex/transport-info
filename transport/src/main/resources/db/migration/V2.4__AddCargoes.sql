INSERT INTO cargo
(id, description, status, weight, delivery_id, endDepot_id, startDepot_id)
VALUES
(1, 'IKEA Sofa', 'PREPARED', 200, 2, 4, 3),
(2, 'IKEA Table', 'PREPARED', 100, 2, 5, 3),
(3, 'Tesla Model X', 'PREPARED', 2300, null, 6, 3),
(4, 'BMW Engine', 'PREPARED', 175, null, 7, 3),
(5, 'Spare Parts', 'PREPARED', 400, 1, 4, 1),
(6, 'Super Computer', 'PREPARED', 900, null, 5, 1),
(7, 'Cab Equipment', 'PREPARED', 430, null, 9, 8),
(8, 'Wheels', 'PREPARED', 520, null, 9, 8),
(9, 'Tomograph', 'PREPARED', 1000, null, 10, 8),
(10, 'Surgical Instruments', 'PREPARED', 200, null, 10, 8),
(11, 'Spinners', 'PREPARED', 860, null, 11, 8),
(12, 'Vapes', 'PREPARED', 650, null, 11, 8),
(13, 'Floating Shelves', 'PREPARED', 600, null, 12, 8),
(14, 'C++ Books', 'PREPARED', 420, null, 12, 8);
