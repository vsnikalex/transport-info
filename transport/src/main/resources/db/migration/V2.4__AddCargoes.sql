INSERT INTO cargo
(id, description, status, weight, delivery_id, endDepot_id, startDepot_id)
VALUES
(1, 'IKEA Sofa', 'PREPARED', 200, 2, 4, 3),
(2, 'IKEA Table', 'PREPARED', 100, 2, 5, 3),
(3, 'Tesla Model X', 'PREPARED', 2300, null, 6, 3),
(4, 'BMW Engine', 'PREPARED', 175, null, 7, 3),
(5, 'Spare Parts', 'PREPARED', 400, 1, 4, 1),
(6, 'Super Computer', 'PREPARED', 900, null, 5, 1);
