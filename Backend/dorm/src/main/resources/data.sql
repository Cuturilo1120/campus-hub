-- =============================================
-- DORM  (INSERT IGNORE — safe to re-run with ddl-auto=update)
-- =============================================
INSERT IGNORE INTO dorm (id, name, city) VALUES (1, 'Studentski Dom Novi Sad', 'Novi Sad');

-- =============================================
-- PAVILION
-- =============================================
INSERT IGNORE INTO pavilion (id, number, address, dorm_id) VALUES (1, 1, 'Bulevar cara Lazara 1', 1);

-- =============================================
-- ROOM  (capacity=2 → 1 slot taken by the seeded DormStay below, 1 available)
-- =============================================
INSERT IGNORE INTO room (id, room_number, capacity, pavilion_id) VALUES (1, '101', 1, 1);

-- =============================================
-- ROOM APPLICATION  (student id=1, IN_PROGRESS — ready to test accept/reject)
-- =============================================
INSERT IGNORE INTO room_application (id, date_of_application, status, student_id, dorm_id) VALUES (1, '2026-02-24', 'IN_PROGRESS', 1, 1);

-- =============================================
-- DORM STAY  (student id=2, ACTIVE — already checked in)
-- =============================================
INSERT IGNORE INTO dorm_stay (id, move_in_date, move_out_date, stay_status, student_id, room_id) VALUES (1, '2026-01-01', '2027-01-01', 'ACTIVE', 2, 1);
