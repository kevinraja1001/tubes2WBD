-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 11 Nov 2016 pada 17.04
-- Versi Server: 10.1.8-MariaDB
-- PHP Version: 5.6.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `karenawbd_akun`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `user`
--

CREATE TABLE `user` (
  `id` int(5) NOT NULL,
  `full_name` varchar(35) NOT NULL,
  `username` varchar(12) NOT NULL,
  `email` varchar(30) NOT NULL,
  `password` varchar(35) NOT NULL,
  `address` varchar(100) NOT NULL,
  `postal_code` varchar(5) NOT NULL,
  `phone_number` varchar(14) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `user`
--

INSERT INTO `user` (`id`, `full_name`, `username`, `email`, `password`, `address`, `postal_code`, `phone_number`) VALUES
(3, '', '', '', '', '', '', ''),
(6, 'End', 'aaa', 'aaa@aaa.aaa', 'bbb', 'aaa aaaa', '11111', '085777713044'),
(2, 'asd', 'asd', 'asd@aa.cc', 'aaa', 'asda', 'asd', 'asdas'),
(5, 'Nugroho S', 'nug', 'massatriya@aaa.com', 'b', 'Jl ciung', '12345', '085777713044'),
(4, 'Nugroho Satriyanto', 'nugsky', 'massatriya@gmail.com', 'a', 'Jl Ciung no 5', '40133', '85777713044'),
(1, 'Rio Chandra Rajagukguk', 'rio', 'rio@aa.aa', '123', 'Jalan Ganesha 10', '40135', '112');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`username`),
  ADD UNIQUE KEY `email` (`email`),
  ADD UNIQUE KEY `id` (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
