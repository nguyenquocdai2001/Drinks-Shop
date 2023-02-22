-- phpMyAdmin SQL Dump
-- version 5.1.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 17, 2022 at 12:03 PM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 7.4.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `coffeeshop`
--

-- --------------------------------------------------------

--
-- Table structure for table `categories`
--

CREATE TABLE `categories` (
  `id_category` int(100) NOT NULL,
  `category_name` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `description` varchar(200) CHARACTER SET utf8 DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `categories`
--

INSERT INTO `categories` (`id_category`, `category_name`, `description`) VALUES
(3, 'Coffee', 'Black Coffee, Brown Coffee'),
(4, 'Milk Tea', 'Milk Tea'),
(5, 'Tea', 'Tea');

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE `products` (
  `id_product` int(100) NOT NULL,
  `id_category` int(100) DEFAULT NULL,
  `product_name` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  `cost` int(11) DEFAULT NULL,
  `product_desc` varchar(200) CHARACTER SET utf8 DEFAULT NULL,
  `photo` varchar(64) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`id_product`, `id_category`, `product_name`, `price`, `cost`, `product_desc`, `photo`) VALUES
(11, 3, 'Black Coffee', 15, 10, 'Black Coffee with sugar', 'Black Coffee.jpeg'),
(12, 3, 'Brown Coffee', 20, 12, 'Brown Coffee with milk', 'Brown Coffee.jpeg'),
(13, 3, 'Bottle Of Coffee', 45, 20, 'Bottle Of Coffe', 'Bottle Of Coffee.jpeg'),
(14, 3, 'Hot Coffee', 15, 12, 'Hot Coffee', 'Hot Coffee.jpeg'),
(15, 3, 'Hot Black Coffee', 15, 10, 'Hot Black Coffee', 'Hot Black Coffee.jpeg'),
(16, 3, 'Hot Milk Coffee', 18, 12, 'Hot Milk Coffee', 'Hot Milk Coffee.jpeg'),
(19, 4, 'Pearl Milk Tea ', 20, 10, 'Pearl Milk Tea with sugar', 'Pearl Milk Tea .jpeg'),
(20, 4, 'Hot Chocolate Milk Tea', 25, 12, 'Hot Chocolate Milk Tea', 'Hot Chocolate Milk Tea.jpeg'),
(21, 4, 'Hot Coconut Milk Tea', 25, 12, 'Hot Coconut Milk Tea', 'Hot Coconut Milk Tea.jpeg'),
(22, 4, 'Red Milk Tea Macchiato', 30, 15, 'Red Milk Tea Macchiato', 'Red Milk Tea Macchiato.jpeg'),
(23, 4, 'Macadamia Milk Tea', 30, 15, 'Macadamia Milk Tea', 'Macadamia Milk Tea.jpeg'),
(24, 4, 'Hot Oolong Milk Tea', 25, 12, 'Hot Oolong Milk Tea', 'Hot Oolong Milk Tea.jpeg'),
(25, 5, 'Lotus Tea', 25, 12, 'Lotus Seed Tea', 'Lotus Tea.jpeg'),
(26, 5, 'Peach Tea', 25, 12, 'Peach Tea', 'Peach Tea.jpeg'),
(27, 5, 'Hot Peach Tea', 20, 10, 'Hot Peach Tea', 'Hot Peach Tea.jpeg'),
(28, 5, 'Hot Lotus Tea', 20, 10, 'Hot Lotus Tea', 'Hot Lotus Tea.jpeg'),
(29, 5, 'Chia Tea', 20, 10, 'Chia Seed Tea', 'Chia Tea.jpeg'),
(30, 5, 'Bottle of Peach Tea', 50, 30, 'Bottle of Peach Tea', 'Bottle of Peach Tea.jpeg');

-- --------------------------------------------------------

--
-- Table structure for table `receipt`
--

CREATE TABLE `receipt` (
  `receipt_id` bigint(20) NOT NULL,
  `receipt_address` varchar(255) DEFAULT NULL,
  `receipt_mail` varchar(255) DEFAULT NULL,
  `receipt_name` varchar(255) DEFAULT NULL,
  `receipt_username` varchar(255) DEFAULT NULL,
  `receipt_total` double DEFAULT NULL,
  `receipt_phone` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `receipt`
--

INSERT INTO `receipt` (`receipt_id`, `receipt_address`, `receipt_mail`, `receipt_name`, `receipt_username`, `receipt_total`, `receipt_phone`) VALUES
(15, '41f/17 Dang Thuy Tram, Binh Thanh', 'nguyenhoainam@gmail.com', 'Nguyen Hoai Nam', 'nguyenhoainam', 140, '1234567890'),
(16, '83 KDC Kim Son, Quan 7', 'hoangthaibaokha@gmail.com', 'Hoang Thai Bao Kha', 'hoangthaibaokha', 125, '1234567899'),
(17, '89 Phan Dang Luu, Binh Thanh', 'nguyentrithanh@gmail.com', 'Nguyen Tri Thanh', 'nguyentrithanh', 210, '1234567888'),
(18, 'test Dia Chi test test', 'testusername11@gmail.com', 'test', 'testusername', 30, '1234567890');

-- --------------------------------------------------------

--
-- Table structure for table `receipt_item`
--

CREATE TABLE `receipt_item` (
  `receipt_item_id` bigint(20) NOT NULL,
  `receipt_item_price` double NOT NULL,
  `receipt_item_quantity` int(11) NOT NULL,
  `receipt_id` bigint(20) DEFAULT NULL,
  `id_product` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `receipt_item`
--

INSERT INTO `receipt_item` (`receipt_item_id`, `receipt_item_price`, `receipt_item_quantity`, `receipt_id`, `id_product`) VALUES
(20, 15, 2, 15, 11),
(21, 20, 1, 15, 12),
(22, 45, 2, 15, 13),
(23, 20, 1, 16, 19),
(24, 25, 1, 16, 20),
(25, 25, 2, 16, 21),
(26, 30, 1, 16, 22),
(27, 25, 1, 17, 25),
(28, 25, 1, 17, 26),
(29, 20, 1, 17, 27),
(30, 20, 1, 17, 28),
(31, 20, 1, 17, 29),
(32, 50, 2, 17, 30),
(33, 15, 2, 18, 11);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(255) NOT NULL,
  `username` varchar(255) CHARACTER SET utf8 NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `fullname` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `gender` int(255) DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `confirmpassword` varchar(255) CHARACTER SET utf8 DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `username`, `password`, `fullname`, `email`, `phone`, `gender`, `address`, `confirmpassword`) VALUES
(1, 'nguyenquocdai', 'e10adc3949ba59abbe56e057f20f883e', 'Nguyen Quoc Dai', 'nguyenquocdai@gmail.com', '0399254540', 1, 'Quan 7', 'e10adc3949ba59abbe56e057f20f883e'),
(30, 'nguyenhoainam', 'e10adc3949ba59abbe56e057f20f883e', 'Nguyen Hoai Nam', 'nguyenhoainam@gmail.com', '1234567890', 1, '41f/17 Dang Thuy Tram, Binh Thanh', '4266bf8d3dc65bc84fd3badf2edfdbe7'),
(31, 'hoangthaibaokha', 'e10adc3949ba59abbe56e057f20f883e', 'Hoang Thai Bao Kha', 'hoangthaibaokha@gmail.com', '1234567899', 1, '83 KDC Kim Son, Quan 7', '4266bf8d3dc65bc84fd3badf2edfdbe7'),
(32, 'nguyentrithanh', 'e10adc3949ba59abbe56e057f20f883e', 'Nguyen Tri Thanh', 'nguyentrithanh@gmail.com', '1234567888', 1, '89 Phan Dang Luu, Binh Thanh', 'e10adc3949ba59abbe56e057f20f883e'),
(33, 'huynhnguyenmyduyen', 'e10adc3949ba59abbe56e057f20f883e', 'Hu&#7923;nh Nguy&#7877;n M&#7929; Duy�n', 'huynhnguyenmyduyen@gmail.com', '1234567777', 0, '41 Hiep Binh Chanh, Thu Duc', 'e10adc3949ba59abbe56e057f20f883e'),
(34, 'testusername', 'fcea920f7412b5da7be0cf42b8c93759', 'test', 'testusername@gmail.com', '0399254546', 1, 'test Dia Chi', 'fcea920f7412b5da7be0cf42b8c93759');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `categories`
--
ALTER TABLE `categories`
  ADD PRIMARY KEY (`id_category`);

--
-- Indexes for table `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`id_product`);

--
-- Indexes for table `receipt`
--
ALTER TABLE `receipt`
  ADD PRIMARY KEY (`receipt_id`);

--
-- Indexes for table `receipt_item`
--
ALTER TABLE `receipt_item`
  ADD PRIMARY KEY (`receipt_item_id`),
  ADD KEY `FKhsefidgsk2bm24y0lcd8q5qgq` (`id_product`),
  ADD KEY `FKsohgmt8ntavcgj10ha2duc8la` (`receipt_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `categories`
--
ALTER TABLE `categories`
  MODIFY `id_category` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `products`
--
ALTER TABLE `products`
  MODIFY `id_product` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT for table `receipt`
--
ALTER TABLE `receipt`
  MODIFY `receipt_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `receipt_item`
--
ALTER TABLE `receipt_item`
  MODIFY `receipt_item_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=35;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `receipt_item`
--
ALTER TABLE `receipt_item`
  ADD CONSTRAINT `FKhsefidgsk2bm24y0lcd8q5qgq` FOREIGN KEY (`id_product`) REFERENCES `products` (`id_product`),
  ADD CONSTRAINT `FKsohgmt8ntavcgj10ha2duc8la` FOREIGN KEY (`receipt_id`) REFERENCES `receipt` (`receipt_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
