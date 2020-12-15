-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Jun 23, 2019 at 09:44 PM
-- Server version: 5.7.24
-- PHP Version: 7.2.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `todoapp`
--

-- --------------------------------------------------------

--
-- Table structure for table `project`
--

CREATE TABLE `project` (
  `proj_id` int(11) NOT NULL,
  `proj_title` varchar(15) DEFAULT NULL,
  `proj_desc` varchar(255) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `project`
--

INSERT INTO `project` (`proj_id`, `proj_title`, `proj_desc`, `user_id`) VALUES
(1, 'ioi', 'ioji', NULL),
(2, 'test', 'network', NULL),
(3, '13', 'defdef', NULL),
(4, NULL, NULL, NULL),
(9, 'ftg', 'gtg', NULL),
(11, '124', '124', NULL),
(12, 'fdhg', 'ret', NULL),
(13, 'vdvdv', 'cdscde', NULL),
(14, 'fdhgfsfsfsfs', 'fsfsfsfsfsfsfs', NULL),
(15, 'fdhghjhjhj', 'kjkjkjkjkjkjkjkjk', 1),
(16, 'hu', 'ji', 1),
(17, 'huhu', 'jihu', 1),
(18, 'kjjk', 'nkkh', 1),
(19, 'jere', 'ewrer', 8),
(20, 'lol', ';;', NULL),
(21, '65', '5646', 8),
(22, '65', '5646', 8);

-- --------------------------------------------------------

--
-- Table structure for table `task`
--

CREATE TABLE `task` (
  `task_id` int(11) NOT NULL,
  `task_desc` varchar(255) DEFAULT NULL,
  `task_completed` int(11) DEFAULT '0',
  `proj_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `task`
--

INSERT INTO `task` (`task_id`, `task_desc`, `task_completed`, `proj_id`) VALUES
(2, 'haia', NULL, NULL),
(3, 'hh', 0, NULL),
(4, NULL, NULL, 20),
(5, NULL, NULL, 20),
(6, ';\';\'', NULL, 20);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `user_id` int(11) NOT NULL,
  `user_name` varchar(15) DEFAULT NULL,
  `user_pass` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`user_id`, `user_name`, `user_pass`) VALUES
(1, 'hai', '123456'),
(2, 'hasanah', '12345667'),
(3, '123', '12345'),
(4, 'sasasas', 'sasasasasasa'),
(5, '12345', '123445'),
(6, 'kautsar', 'hasanah'),
(7, '1111', '11111'),
(8, 'aaaaa', 'aaaaa');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `project`
--
ALTER TABLE `project`
  ADD PRIMARY KEY (`proj_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `task`
--
ALTER TABLE `task`
  ADD PRIMARY KEY (`task_id`),
  ADD KEY `proj_id` (`proj_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`user_id`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `project`
--
ALTER TABLE `project`
  ADD CONSTRAINT `project_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`);

--
-- Constraints for table `task`
--
ALTER TABLE `task`
  ADD CONSTRAINT `task_ibfk_1` FOREIGN KEY (`proj_id`) REFERENCES `project` (`proj_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
