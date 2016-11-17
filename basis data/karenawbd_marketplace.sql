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
-- Database: `karenawbd_marketplace`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `like`
--

CREATE TABLE `like` (
  `user_id` int(5) NOT NULL,
  `produk_id` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `like`
--

INSERT INTO `like` (`user_id`, `produk_id`) VALUES
(1, 1),
(1, 2),
(1, 3),
(2, 1),
(4, 3),
(4, 1),
(4, 2),
(4, 21),
(4, 20);

-- --------------------------------------------------------

--
-- Struktur dari tabel `pembelian`
--

CREATE TABLE `pembelian` (
  `id` int(5) NOT NULL,
  `consignee` varchar(35) NOT NULL,
  `address` varchar(150) NOT NULL,
  `postal_code` varchar(5) NOT NULL,
  `phone` varchar(13) NOT NULL,
  `id_produk` int(5) NOT NULL,
  `nama_produk` varchar(30) NOT NULL,
  `url_pict` varchar(60) NOT NULL,
  `harga` int(10) NOT NULL,
  `jumlah` int(3) NOT NULL,
  `penjual` varchar(12) NOT NULL,
  `pembeli` varchar(12) NOT NULL,
  `tanggal_beli` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `pembelian`
--

INSERT INTO `pembelian` (`id`, `consignee`, `address`, `postal_code`, `phone`, `id_produk`, `nama_produk`, `url_pict`, `harga`, `jumlah`, `penjual`, `pembeli`, `tanggal_beli`) VALUES
(6, 'Nugroho Satriyanto', 'Jl Ciung no 5\r\nSadang Serang, Coblong', '40133', '85777713044', 2, 'TOEFL', '../img/test.png', 91100, 2, 'rio', 'nugsky', '2016-09-29 07:27:16'),
(7, 'Lebah Ganteng', 'Jl Ganool', '51123', '0863183713', 1, 'Buku sejarah', '../img/test.png', 55990, 10, 'rio', 'nugsky', '2016-09-29 07:38:29'),
(8, 'a', 'b', 'c', 'd', 2, 'TOEFL', '../img/test.png', 91100, 3, 'rio', 'nug', '2016-09-29 07:50:48'),
(9, 'Nugroho Satriyanto', 'Jl Ciung no 5\r\nSadang Serang, Coblong aaaaaaaaaaa aaaaaaaaaaa', '40133', '85777713044', 15, 'aaaaaaaaaa', '../img/S_4898451273014.jpg', 50000, 1, 'nugsky', 'nugsky', '2016-10-07 13:03:01'),
(10, 'Nugroho Satriyanto', 'Jl Ciung no 5\r\nSadang Serang, Coblong', '54111', '085777713044', 21, 'coba', '../img/S_4898451273014.jpg', 10000, 1, 'nugsky', 'nugsky', '2016-10-17 07:30:32'),
(11, 'Nugroho Satriyanto', 'aaaaaaaaaaaaa', '1111', '085777713044', 20, 'b', '../img/Screenshot_20161014-170554.png', 10000, 1, 'nugsky', 'nugsky', '2016-10-17 07:45:33');

-- --------------------------------------------------------

--
-- Struktur dari tabel `produk`
--

CREATE TABLE `produk` (
  `id` int(5) NOT NULL,
  `name` varchar(30) NOT NULL,
  `desc` varchar(250) DEFAULT NULL,
  `price` int(10) NOT NULL,
  `url_pict` varchar(60) NOT NULL,
  `penjual` varchar(12) NOT NULL,
  `j_like` int(5) DEFAULT '0',
  `tanggal_add` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'tanggal barang ditambahkan'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `produk`
--

INSERT INTO `produk` (`id`, `name`, `desc`, `price`, `url_pict`, `penjual`, `j_like`, `tanggal_add`) VALUES
(1, 'aabb', 'aabb', 5500, '../img/test.png', 'rio', 0, '2016-09-26 12:53:48'),
(2, 'TOEFL', 'hello universe :), fun learning', 91100, '../img/test.png', 'rio', 0, '2016-09-26 12:53:48'),
(3, 'Buku Catatan', 'menulis dengan indah', 2990, '../img/test.png', 'asd', 0, '2016-09-26 12:53:48'),
(19, 'coba', '', 50000, '../img/tumblr_o4fohpTGCW1qc1pg3o6_540.gif', 'nugsky', 0, '2016-10-17 06:29:15'),
(20, '', '', 0, '../img/Screenshot_20161014-170554.png', 'nugsky', 0, '2016-10-17 06:35:03'),
(21, 'coba', 'gggggggggggggggggggggggggggggggggggggg', 10000, '../img/S_4898451273014.jpg', 'nugsky', 0, '2016-10-17 07:25:12');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `like`
--
ALTER TABLE `like`
  ADD KEY `user_id` (`user_id`),
  ADD KEY `produk_id` (`produk_id`);

--
-- Indexes for table `pembelian`
--
ALTER TABLE `pembelian`
  ADD PRIMARY KEY (`id`),
  ADD KEY `pembeli` (`penjual`),
  ADD KEY `pembeli_2` (`pembeli`);

--
-- Indexes for table `produk`
--
ALTER TABLE `produk`
  ADD PRIMARY KEY (`id`),
  ADD KEY `penjual` (`penjual`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `pembelian`
--
ALTER TABLE `pembelian`
  MODIFY `id` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
--
-- AUTO_INCREMENT for table `produk`
--
ALTER TABLE `produk`
  MODIFY `id` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;
--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `like`
--
ALTER TABLE `like`
  ADD CONSTRAINT `like_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `like_ibfk_2` FOREIGN KEY (`produk_id`) REFERENCES `produk` (`id`);

--
-- Ketidakleluasaan untuk tabel `pembelian`
--
ALTER TABLE `pembelian`
  ADD CONSTRAINT `pembelian_ibfk_3` FOREIGN KEY (`penjual`) REFERENCES `user` (`username`),
  ADD CONSTRAINT `pembelian_ibfk_4` FOREIGN KEY (`pembeli`) REFERENCES `user` (`username`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
