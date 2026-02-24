-- =============================================
-- CARDS  (INSERT IGNORE — safe to re-run with ddl-auto=update)
-- card id=1 → student id=1 (BUDGET),  card id=2 → student id=2 (SELF_FINANCE)
-- =============================================
INSERT IGNORE INTO card (id, student_id, balance, expiration_date, study_status) VALUES (1, 1, 500.00, '2027-02-24', 'BUDGET');
INSERT IGNORE INTO card (id, student_id, balance, expiration_date, study_status) VALUES (2, 2, 200.00, '2027-02-24', 'SELF_FINANCE');

-- =============================================
-- MEALS  (3 per card — one row per MealType)
-- =============================================
INSERT IGNORE INTO meal (id, card_id, meal_type, amount) VALUES (1, 1, 'BREAKFAST', 3);
INSERT IGNORE INTO meal (id, card_id, meal_type, amount) VALUES (2, 1, 'LUNCH',     3);
INSERT IGNORE INTO meal (id, card_id, meal_type, amount) VALUES (3, 1, 'DINNER',    3);

INSERT IGNORE INTO meal (id, card_id, meal_type, amount) VALUES (4, 2, 'BREAKFAST', 2);
INSERT IGNORE INTO meal (id, card_id, meal_type, amount) VALUES (5, 2, 'LUNCH',     2);
INSERT IGNORE INTO meal (id, card_id, meal_type, amount) VALUES (6, 2, 'DINNER',    2);
