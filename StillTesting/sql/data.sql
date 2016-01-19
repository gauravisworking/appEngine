
INSERT INTO `guestbook`.`house` (`id`, `name`, `address`) VALUES ('1', 'Vatsalya', 'N4,CIDCO');


INSERT INTO `guestbook`.`device_type` (`id`, `name`, `digital_pins`, `analog_pins`, `pwm_pins`) VALUES ('1', 'ESP - 2560', '1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30', '31,32,33,34,35,36', '37,38,39,40,41,42,43');


INSERT INTO `guestbook`.`point_type` (`id`, `name`) VALUES ('1', 'LIGHT');
INSERT INTO `guestbook`.`point_type` (`id`, `name`) VALUES ('2', 'FAN');
INSERT INTO `guestbook`.`point_type` (`id`, `name`) VALUES ('3', 'RBG_LIGHT');


INSERT INTO `guestbook`.`actoin` (`id`, `name`, `pin_type`) VALUES ('1', 'ON', 'DIGITAL');
INSERT INTO `guestbook`.`actoin` (`id`, `name`, `pin_type`) VALUES ('2', 'OFF', 'DIGITAL');
INSERT INTO `guestbook`.`actoin` (`id`, `name`, `pin_type`) VALUES ('3', 'MONITOR', 'DIGITAL');
INSERT INTO `guestbook`.`actoin` (`id`, `name`, `pin_type`) VALUES ('4', 'PWM', 'PWM');


INSERT INTO `guestbook`.`action_to_point_type_map` (`point_type_fk`, `action_fk`) VALUES ('1', '1');
INSERT INTO `guestbook`.`action_to_point_type_map` (`point_type_fk`, `action_fk`) VALUES ('1', '2');
INSERT INTO `guestbook`.`action_to_point_type_map` (`point_type_fk`, `action_fk`) VALUES ('1', '3');
INSERT INTO `guestbook`.`action_to_point_type_map` (`point_type_fk`, `action_fk`) VALUES ('2', '1');
INSERT INTO `guestbook`.`action_to_point_type_map` (`point_type_fk`, `action_fk`) VALUES ('2', '2');
INSERT INTO `guestbook`.`action_to_point_type_map` (`point_type_fk`, `action_fk`) VALUES ('2', '3');
INSERT INTO `guestbook`.`action_to_point_type_map` (`point_type_fk`, `action_fk`) VALUES ('2', '4');
INSERT INTO `guestbook`.`action_to_point_type_map` (`point_type_fk`, `action_fk`) VALUES ('3', '1');
INSERT INTO `guestbook`.`action_to_point_type_map` (`point_type_fk`, `action_fk`) VALUES ('3', '2');
INSERT INTO `guestbook`.`action_to_point_type_map` (`point_type_fk`, `action_fk`) VALUES ('3', '3');
INSERT INTO `guestbook`.`action_to_point_type_map` (`point_type_fk`, `action_fk`) VALUES ('3', '4');

