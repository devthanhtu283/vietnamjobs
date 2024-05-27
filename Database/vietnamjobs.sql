-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th5 18, 2024 lúc 08:18 AM
-- Phiên bản máy phục vụ: 10.4.28-MariaDB
-- Phiên bản PHP: 8.0.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `vietnamjobs`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `account`
--

CREATE TABLE `account` (
  `id` int(11) NOT NULL,
  `username` varchar(250) NOT NULL,
  `password` varchar(250) NOT NULL,
  `type_account` int(11) NOT NULL,
  `created` date NOT NULL,
  `email` varchar(400) NOT NULL,
  `status` tinyint(1) NOT NULL,
  `security_code` varchar(250) NOT NULL,
  `wallet` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `account`
--

INSERT INTO `account` (`id`, `username`, `password`, `type_account`, `created`, `email`, `status`, `security_code`, `wallet`) VALUES
(2, 'acc1', '$2a$10$VBUOUXEm0VDNpie1qcw5p.S4f.XWk9kjFhlp1qG5vPHEWAKlDKAbG', 1, '2024-03-28', 'letu36592@gmail.com', 1, '123456', 3500001),
(3, 'acc2', '$2a$10$IdUEnM1LMVhsQTsoIGL9AONDvWwvDnvEcAgsQv/iHu4VE3Jj.X8IG', 2, '2024-03-29', 'abc@gmail.com', 0, '123456', 162745),
(4, 'admin1', '$2a$10$5bh4HNfE4Q7HfP39wTNIa.UBhWO4CVxjTNlrT3a97hHuE/978LRkS', 3, '2024-04-29', 'employer1@gmail.com', 1, '123456', 1),
(5, 'admin', '$2a$10$Gp21raY5TB/WmTtx6ZOCouy77LPkmuSBXqbuxpvE6ikFgPSVIgDqe', 3, '2024-05-06', 'letu36592@gmail.com', 1, 'MVPisH8cOL', 0),
(6, 'thaihoan', '$2a$10$qIrooDc2SV8VE9tuZxYky.a46z3Qk6yiYg8DCt/txGf/56jGfKKM6', 1, '2024-05-06', 'devthanhtu283@gmail.com', 0, 'MgZOMmSXHT', 0),
(7, 'acc4', '$2a$10$5bh4HNfE4Q7HfP39wTNIa.UBhWO4CVxjTNlrT3a97hHuE/978LRkS', 1, '2024-03-29', 'devthanhtu283@gmail.com', 1, '123456', 1),
(9, 'employer1', '$2a$10$Gp21raY5TB/WmTtx6ZOCouy77LPkmuSBXqbuxpvE6ikFgPSVIgDqe', 2, '2024-02-11', 'toighetloptoi1@gmail.com', 1, 'ZW3AlTgp5OGSYWCbeFEc', 0),
(11, 'test1', '$2a$10$1XXWTqTFKxdkgLaugnuAWO/CaC/iTTOnqQ/Kv6yufM9b7LoUJ3Piq', 2, '2024-02-15', 'toighetloptoi@gmail.com', 1, 'xkt6HgAihm', 0),
(12, 'employer2', '$2a$10$5bh4HNfE4Q7HfP39wTNIa.UBhWO4CVxjTNlrT3a97hHuE/978LRkS', 2, '2024-05-11', 'toighetloptoi1@gmail.com', 1, 'ZW3AlTgp5OGSYWCbeFEc', 0),
(13, 'employer3', '$2a$10$5bh4HNfE4Q7HfP39wTNIa.UBhWO4CVxjTNlrT3a97hHuE/978LRkS', 2, '2024-01-11', 'toighetloptoi1@gmail.com', 1, 'ZW3AlTgp5OGSYWCbeFEc', 0),
(14, 'employer4', '$2a$10$Gp21raY5TB/WmTtx6ZOCouy77LPkmuSBXqbuxpvE6ikFgPSVIgDqe', 2, '2024-01-11', 'toighetloptoi1@gmail.com', 1, 'ZW3AlTgp5OGSYWCbeFEc', 410000),
(15, 'employer5', '$2a$10$5bh4HNfE4Q7HfP39wTNIa.UBhWO4CVxjTNlrT3a97hHuE/978LRkS', 2, '2024-05-11', 'toighetloptoi1@gmail.com', 1, 'ZW3AlTgp5OGSYWCbeFEc', 0),
(16, 'employer6', '$2a$10$5bh4HNfE4Q7HfP39wTNIa.UBhWO4CVxjTNlrT3a97hHuE/978LRkS', 2, '2024-05-11', 'toighetloptoi1@gmail.com', 1, 'ZW3AlTgp5OGSYWCbeFEc', 0),
(18, 'employer7', '$2a$10$Gp21raY5TB/WmTtx6ZOCouy77LPkmuSBXqbuxpvE6ikFgPSVIgDqe', 2, '2024-05-15', 'tuleep.pf@gmail.com', 1, 'LEgLt482qo', -52531),
(19, 'employer10', '$2a$10$sXLms9R6ZcS51SuELMBk4eWJnnqqyqkTdsVUYc8S9qNoR5UxzB5cy', 2, '2024-05-15', 'toighetloptoi1@gmail.com', 1, 'L5m6qZltId', 400000),
(23, 'ungvien1', '$2a$10$U/ugNGrP6Pl9vbEW..NTLe3wPGzxUAiWWnX6zWX/O7zS8T5j9fMAm', 1, '2024-05-17', 'devthanhtu283@gmail.com', 1, '9Bu9Ld1hai', 0),
(24, 'nhatuyendung1', '$2a$10$C3274gCvTttAbTtpnm16ROA3fzTv9QKbHZ1HzMbeu80la.eVDpaJe', 2, '2024-05-17', '21130594@st.hcmuaf.edu.vn', 1, 'bbpU41wgF6', 450000);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `admin`
--

CREATE TABLE `admin` (
  `id` int(11) NOT NULL,
  `account_id` int(11) NOT NULL,
  `fullname` varchar(250) NOT NULL,
  `phone` varchar(100) NOT NULL,
  `photo` varchar(250) NOT NULL,
  `status` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `admin`
--

INSERT INTO `admin` (`id`, `account_id`, `fullname`, `phone`, `photo`, `status`) VALUES
(1, 4, 'Lê Thanh Tú', '0898340069', 'avatar1.jpg', 0),
(2, 3, 'Trần Tấn Lộc', '0898340069', 'avatar2.jpg', 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `application_history`
--

CREATE TABLE `application_history` (
  `id` int(11) NOT NULL,
  `created` date NOT NULL,
  `postings_id` int(11) NOT NULL,
  `seeker_id` int(11) NOT NULL,
  `status` int(11) NOT NULL,
  `result` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `application_history`
--

INSERT INTO `application_history` (`id`, `created`, `postings_id`, `seeker_id`, `status`, `result`) VALUES
(5, '2024-05-13', 12, 1, 0, 0),
(6, '2024-05-15', 13, 1, 0, 0),
(9, '2024-05-18', 28, 1, 1, 1),
(13, '2024-05-17', 12, 7, 0, 0),
(14, '2024-05-17', 32, 7, 1, 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `category`
--

CREATE TABLE `category` (
  `id` int(11) NOT NULL,
  `name` varchar(250) NOT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `status` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `category`
--

INSERT INTO `category` (`id`, `name`, `parent_id`, `status`) VALUES
(6, 'Bảo hiểm', 0, 1),
(7, 'Bảo trì / Sữa chữa', 0, 1),
(8, 'Bất động sản', 0, 1),
(9, 'Chứng  khoán', 0, 1),
(10, 'Cơ khí', 0, 1),
(11, 'Du lịch', 0, 1),
(12, 'Dược phẩm /  Y tế / Công nghệ sinh học', 0, 1),
(13, 'Điện tử / Điện lạnh', 0, 1),
(14, 'Giải trí', 0, 1),
(15, 'Giáo dục / Đào tạo', 0, 1),
(16, 'In ấn / Xuất bản', 0, 1),
(17, 'Internet / Online', 0, 1),
(18, 'IT', 0, 1),
(19, 'Kế toán /  Kiểm toán', 0, 1),
(20, 'Logistics / Vận tải', 0, 1),
(21, 'Luật', 0, 1),
(22, 'Marketing / Truyền thông / Quảng cáo', 0, 1),
(23, 'Bảo trì', 7, 1),
(24, 'Sửa chữa', 7, 1),
(25, 'Dược phẩm', 12, 1),
(26, 'Y tế', 12, 1),
(27, 'Công nghệ sinh học', 12, 1),
(28, 'Điện tử', 13, 1),
(29, 'Điện lạnh', 13, 1),
(30, 'Giáo dục', 15, 1),
(31, 'Đào tạo', 15, 1),
(32, 'In ấn', 16, 1),
(33, 'Xuất bản', 16, 1),
(34, 'Internet', 17, 1),
(35, 'Online', 17, 1),
(36, 'Kế toán', 19, 1),
(37, 'Kiểm toán', 19, 1),
(38, 'Logistics', 20, 1),
(39, 'Vận tải', 20, 1),
(40, 'Marketing', 22, 1),
(41, 'Truyền thông', 22, 1),
(42, 'Quảng cáo', 22, 1),
(43, 'IT Phần mềm', 18, 1),
(44, 'IT Phần cứng', 18, 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `check_news`
--

CREATE TABLE `check_news` (
  `id` int(11) NOT NULL,
  `postings_id` int(11) NOT NULL,
  `seekers_id` int(11) NOT NULL,
  `status` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `employer`
--

CREATE TABLE `employer` (
  `id` int(11) NOT NULL,
  `account_id` int(11) NOT NULL,
  `name` varchar(400) DEFAULT NULL,
  `scale` varchar(300) DEFAULT NULL,
  `logo` varchar(250) DEFAULT NULL,
  `link` varchar(400) DEFAULT NULL,
  `description` text DEFAULT NULL,
  `address` text DEFAULT NULL,
  `map_link` text DEFAULT NULL,
  `status` tinyint(1) DEFAULT NULL,
  `cover` varchar(250) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `employer`
--

INSERT INTO `employer` (`id`, `account_id`, `name`, `scale`, `logo`, `link`, `description`, `address`, `map_link`, `status`, `cover`) VALUES
(1, 3, 'MYNAVI CORPORATION', '500+ nhân viên', 'company1.jpg', 'https://www.topcv.vn/cong-ty/the-representative-office-of-mynavi-corporation-in-ho-chi-minh-city/168714.html', 'Thanks to all your support, we will celebrate the 50th anniversary of our foundation on August 15, 2023. We have been able to continue our business to this day thanks to the support of many people, including our users, business partners, employees, and their families. I would like to show my appreciation to all those who have been involved with our company over these past 50 years.\n\nSince our foundation in 1973, our company has provided many users and companies with various services centered around publishing and human resources business. By being the “impetus to success and growth in our user’s lives”, which is implied in the Mynavi logo, we have always strived to develop and provide services that are tailored to an individual’s career with a focus on the human resources business.  \n\n“To create a world that envisions the future by engaging individual potential.”\n\nThis is the world view we try to draw in everything we undertake.\n\nWe are now said to be in the era of volatility, uncertainty, complexity, and ambiguity (VUCA), an environment where it is difficult for individuals to foresee their future lives and careers. In such an era of uncertainty Mynavi Group will continue to face everybody and, expand each individual’s potential and provide new services that are not bound by current ways of life.\n\nGoing forward, the speed and magnitude of social changes in the future will far exceed the levels up until now. Advances in technology will greatly change people’s values, and existing services will be replaced by those which are more convenient and efficient. We will dedicate ourselves to further advancing digital transformation of businesses and use of AI in response to the rapid digital shift, and we will also be committed to offering numerous options to enrich the life of everyone and expand the possibilities of each one’s life.\n\nIn order to make an uncertain future visible, we will strive to be a company that will exist beyond the times as one that can respond to various needs of people and social issues.\n\nI ask for your continued support as we strive to meet your expectations as we move into the next 50 years and beyond.We look forward to your continued support.', 'Phòng S01.04, lầu 1, tòa nhà cirCO, số 63A, đường Nam Kỳ Khởi Nghĩa, phường Bến Thành, Quận 1, Thành phố Hồ Chí Minh', 'https://www.google.com/maps?ll=10.771969,106.700407&z=15&t=m&hl=vi&gl=US&mapclient=embed&cid=2839359268678376356', 1, 'company_cover_1.jpg'),
(2, 15, 'CÔNG TY CỔ PHẦN TẬP ĐOÀN KINGDOOR', '25-99 nhân viên', 'company4.jpg', 'https://www.topcv.vn/cong-ty/cong-ty-co-phan-tap-doan-kingdoor/163739.html', 'Công Ty Cổ Phần SX – TM – XNK Kingdoor (Kingdoor JSC) sản xuất và cung cấp cửa gỗ công nghiệp, cửa nhựa giả gỗ, cửa chống cháy', '602 Kinh Dương Vương, phường An Lạc, Quận Bình Tân, TP.HCM', 'abchttps://www.google.com/maps?ll=10.729727,106.609036&z=15&t=m&hl=vi&gl=US&mapclient=embed&q=602+%C4%90.+Kinh+D%C6%B0%C6%A1ng+V%C6%B0%C6%A1ng+An+L%E1%BA%A1c+B%C3%ACnh+T%C3%A2n+Th%C3%A0nh+ph%E1%BB%91+H%E1%BB%93+Ch%C3%AD+Minh', 1, 'company_cover_1.jpg'),
(3, 12, 'CÔNG TY TNHH THỜI TRANG VÀ MỸ PHẨM ÂU CHÂU ', '1000+ nhân viên', 'company3.jpg', 'https://www.topcv.vn/cong-ty/cong-ty-tnhh-thoi-trang-va-my-pham-au-chau-acfc-co-ltd/71155.html', 'ACFC - Thành viên thuộc tập đoàn Imex Pan Pacific Group (IPPG), là nhà phân phối và quản lý các thương hiệu thời trang quốc tế hàng đầu Việt Nam.', 'Tầng 6, Tòa nhà Sài Gòn Royal, 91 Pasteur, Phường Bến Nghé, Quận 1, Thành phố Hồ Chí Minh, Việt Nam', 'https://www.google.com/maps?ll=10.776989,106.699305&z=15&t=m&hl=vi&gl=US&mapclient=embed&cid=7843352016465502472', 1, 'company_cover_1.jpg'),
(4, 13, 'Indochina Junk JSC', '100-499 nhân viên', 'company4.jpg', 'https://www.topcv.vn/cong-ty/indochina-junk-jsc/6245.html', ' Indochina Junk luôn tập trung vào việc phát triển du thuyền sang trọng mang đến cho khách những trải nghiệm tuyệt vời tại Vịnh Hạ Long,', 'Số 1 - Đường Hạ Long - Phường Bãi Cháy - Thành Phố Hạ Long - Tỉnh Quảng Ninh', 'https://www.google.com/maps?ll=20.960158,107.063092&z=15&t=m&hl=vi&gl=US&mapclient=embed&q=1+H%E1%BA%A1+Long+B%C3%A3i+Ch%C3%A1y+Th%C3%A0nh+ph%E1%BB%91+H%E1%BA%A1+Long+Qu%E1%BA%A3ng+Ninh', 1, 'company_cover_1.jpg'),
(5, 14, 'CÔNG TY TNHH THƯƠNG MẠI VÀ DỊCH VỤ CỬU LONG MEKO', '10-24 nhân viên', 'company5.jpg', 'https://mekogroup.vn/', 'MeKo hoạt động trong lĩnh vực Phân phối hàng Điện tử tiêu dùng, Sản phẩm công nghệ và cung cấp các giải pháp phát triển thị trường cho các thương hiệu uy tín trên thế giới. ', 'Tầng lửng – Cao Ốc Đinh Lễ, số 01 Đinh Lễ, Phường 13, Quận 4, Thành phố Hồ Chí Minh', 'https://www.google.com/maps?ll=10.764066,106.705063&z=15&t=m&hl=vi&gl=US&mapclient=embed&cid=1649888228150027215', 1, 'company_cover_1.jpg'),
(6, 16, 'CÔNG TY CỔ PHẦN NETSMART', '25-99 nhân viên', 'company6.jpg', 'http://netsmart.vn/GioiThieuForm.aspx', 'Hoạt động chính của công ty Cổ phần NETSMART là hoạt động thương mại, kinh doanh sản phẩm Công nghệ thông tin tại thị trường Việt Nam', '46A, Ngõ 120, đường Trường Chinh, phường Phương Mai, quận Đống Đa, thành phố Hà Nội', 'https://www.google.com/maps?ll=21.000949,105.835095&z=15&t=m&hl=vi&gl=US&mapclient=embed&q=46a+Ng.+120+%C4%90.+Tr%C6%B0%E1%BB%9Dng+Chinh+Ph%C6%B0%C6%A1ng+%C4%90%C3%ACnh+%C4%90%E1%BB%91ng+%C4%90a+H%C3%A0+N%E1%BB%99i', 1, 'company_cover_1.jpg'),
(40, 18, 'Cong Ty Thanh Tu', 'O người', 'company1.jpg', 'yourlink', 'Your Description', 'your Address', '', 1, 'company_cover_1.jpg'),
(41, 19, 'Thanh Tu', '10 người', 'company1.jpg', 'yourlink', 'Your Description', 'your Address', 'aaaaa', 1, 'company_cover_1.jpg'),
(42, 24, 'Thanh Tu company', 'O người', 'company1.jpg', 'yourlink', 'Your Description', 'your Address', '', 1, 'company_cover_1.jpg');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `experience`
--

CREATE TABLE `experience` (
  `id` int(11) NOT NULL,
  `name` varchar(250) NOT NULL,
  `status` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `experience`
--

INSERT INTO `experience` (`id`, `name`, `status`) VALUES
(1, 'Chưa có kinh nghiệm', 1),
(3, 'Dưới 1 năm', 1),
(4, '1 năm', 1),
(5, '2 năm', 1),
(6, '3 năm', 1),
(7, '4 năm', 1),
(8, '5 năm', 1),
(9, 'Trên 5 năm', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `follow`
--

CREATE TABLE `follow` (
  `id` int(11) NOT NULL,
  `employer_id` int(11) NOT NULL,
  `seeker_id` int(11) NOT NULL,
  `status` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `follow`
--

INSERT INTO `follow` (`id`, `employer_id`, `seeker_id`, `status`) VALUES
(1, 2, 1, 1),
(5, 1, 1, 1),
(6, 2, 4, 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `keyword`
--

CREATE TABLE `keyword` (
  `id` int(11) NOT NULL,
  `name` varchar(250) NOT NULL,
  `category_id` int(11) NOT NULL,
  `status` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `local`
--

CREATE TABLE `local` (
  `id` int(11) NOT NULL,
  `name` varchar(250) NOT NULL,
  `status` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `local`
--

INSERT INTO `local` (`id`, `name`, `status`) VALUES
(1, 'Hồ Chí Minh', 1),
(2, 'Hà Nội', 1),
(3, 'Đà Nẵng', 1),
(4, 'Hải Phòng', 1),
(5, 'Huế', 1),
(6, 'Nha Trang', 1),
(7, 'Cần Thơ', 1),
(8, 'Đồng Nai', 1),
(9, 'Bình Dương', 1),
(12, 'Vĩnh Long', 1),
(13, 'Long An', 1),
(14, 'Hải Dương', 1),
(15, 'An Giang', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `payment`
--

CREATE TABLE `payment` (
  `id` int(11) NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,
  `postings_id` int(11) NOT NULL,
  `cost` double NOT NULL,
  `status` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `payment`
--

INSERT INTO `payment` (`id`, `start_date`, `end_date`, `postings_id`, `cost`, `status`) VALUES
(2, '2024-05-07', '2024-05-31', 12, 11, 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `postings`
--

CREATE TABLE `postings` (
  `id` int(11) NOT NULL,
  `employer_id` int(11) NOT NULL,
  `title` varchar(250) NOT NULL,
  `description` text NOT NULL,
  `created` date NOT NULL,
  `deadline` date NOT NULL,
  `gender` varchar(250) NOT NULL,
  `quantity` int(11) NOT NULL,
  `wage_id` int(11) NOT NULL,
  `category_id` int(11) NOT NULL,
  `local_id` int(11) NOT NULL,
  `rank_id` int(11) NOT NULL,
  `type_id` int(11) NOT NULL,
  `experience_id` int(11) NOT NULL,
  `status` tinyint(1) NOT NULL,
  `open` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `postings`
--

INSERT INTO `postings` (`id`, `employer_id`, `title`, `description`, `created`, `deadline`, `gender`, `quantity`, `wage_id`, `category_id`, `local_id`, `rank_id`, `type_id`, `experience_id`, `status`, `open`) VALUES
(12, 1, 'Chuyên Viên Hành Chính Kế Toán ', 'Theo dõi chi tiêu nội bộ; Lưu trữ hồ sơ, kế toán & hành chính nhân sự;.', '2024-05-08', '2024-05-24', 'male', 1, 3, 6, 1, 1, 4, 1, 1, 1),
(13, 2, 'Nhân Viên Kinh Doanh Nội Thất', 'Liên hệ tư vấn, chăm sóc khách hàng từ data marketing, đối tác của công ty hoặc nguồn tự tìm kiếm.', '2024-05-08', '2024-05-24', 'male', 2, 1, 6, 1, 1, 4, 4, 1, 1),
(14, 3, 'Quản Lý Cửa Hàng Thời Trang Cao Cấp ', 'Làm report: hiểu cơ bản về best và worst selling và biết cách sử dụng data đã phân tích để improve số sale.', '2024-04-08', '2024-05-24', 'male', 5, 3, 7, 1, 3, 4, 5, 1, 1),
(15, 4, 'Phó Phòng Quản Lý Dịch Vụ', 'Kiểm soát chất lượng dịch vụ theo tiêu chuẩn', '2024-04-08', '2024-05-24', 'male', 2, 1, 7, 1, 3, 4, 1, 1, 1),
(16, 5, 'Nhân Viên Kỹ Thuật Bảo Hành Sửa Chữa', 'Tiếp nhận thông tin bảo hành từ khách hàng, đại lý;', '2024-04-08', '2024-05-24', 'male', 2, 2, 8, 1, 1, 4, 4, 1, 1),
(17, 5, 'Nhân Viên IT - Kỹ Thuật Công Nghệ Thông Tin', 'Nghiên cứu thiết bị, sản phẩm, giải pháp mạng máy tính, Cisco, Engenius, Planet, Aruba..', '2024-03-08', '2024-05-24', 'male', 2, 2, 8, 2, 1, 4, 5, 1, 1),
(18, 1, 'Tuyển nữ nhân viên tiếp tân', '<h3>M&ocirc; tả c&ocirc;ng việc</h3>\r\n\r\n<p><strong>- Đ&oacute;n tiếp kh&aacute;ch:</strong></p>\r\n\r\n<p>L&agrave; người tiếp x&uacute;c trực tiếp với kh&aacute;ch h&agrave;ng , ch&agrave;o đ&oacute;n kh&aacute;ch h&agrave;ng đến spa.</p>\r\n\r\n<p>Tạo mối quan hệ tốt v&agrave; thường xuy&ecirc;n kết nối với kh&aacute;ch h&agrave;ng</p>\r\n\r\n<p><strong>- Tư vấn dịch vụ, sản phẩm spa v&agrave; Chăm s&oacute;c kh&aacute;ch h&agrave;ng:</strong></p>\r\n\r\n<p>Hiểu r&otilde; về Dịch vụ v&agrave; Sản phẩm của Spa</p>\r\n\r\n<p>Nắm bắt nhu cầu của kh&aacute;ch h&agrave;ng v&agrave; đưa ra tư vấn dịch vụ ph&ugrave; hợp.</p>\r\n\r\n<p>Xử l&yacute; t&igrave;nh huống v&agrave; giải đ&aacute;p c&aacute;c thắc mắc của kh&aacute;ch h&agrave;ng.</p>\r\n\r\n<p><strong>C&ocirc;ng việc kh&aacute;c:</strong></p>\r\n\r\n<p>Kết hợp với c&aacute;c bộ phận kh&aacute;c để xử l&yacute; c&aacute;c sự việc được hiệu quả</p>\r\n\r\n<p>Thực hiện mở ca - kết ca</p>\r\n\r\n<p>Đảm bảo vệ sinh khu vực l&agrave;m việc</p>\r\n\r\n<p>Đề xuất &yacute; tưởng cải tiến hiệu quả c&ocirc;ng việc tốt hơn.</p>\r\n\r\n<p>B&aacute;o c&aacute;o với cấp tr&ecirc;n tất cả c&ocirc;ng việc đ&atilde; thực hiện theo ng&agrave;y, tuần, th&aacute;ng, năm v&agrave; những feedback của kh&aacute;ch h&agrave;ng cho Quản l&yacute; xử l&yacute;.</p>\r\n\r\n<p>Ho&agrave;n th&agrave;nh c&aacute;c c&ocirc;ng việc kh&aacute;c của quản l&yacute; giao cho.</p>\r\n\r\n<h3>Y&ecirc;u cầu ứng vi&ecirc;n</h3>\r\n\r\n<p><strong>- Nữ - c&oacute; ngoại h&igrave;nh ưa nh&igrave;n, Từ 25 - 33 tuổi</strong></p>\r\n\r\n<p><strong>- Giao tiếp lưu lo&aacute;t tiếng Anh</strong></p>\r\n\r\n<p>- Y&ecirc;u th&iacute;ch c&ocirc;ng việc tư vấn, tương t&aacute;c nhiều với kh&aacute;ch h&agrave;ng</p>\r\n\r\n<p>- Linh hoạt, nhanh nhẹn v&agrave; chịu được &aacute;p lực.</p>\r\n\r\n<p>- Ưu ti&ecirc;n ứng vi&ecirc;n c&oacute; kinh nghiệm l&agrave;m tư vấn b&aacute;n h&agrave;ng hoặc từng l&agrave;m việc trong Kh&aacute;ch sạn - Nh&agrave; h&agrave;ng l&agrave; lợi thế</p>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<h3>Quyền lợi</h3>\r\n\r\n<p><strong>- Lương: từ 10 - 17tr (lương cơ bản + thưởng KPI + BONUS từ kh&aacute;ch)</strong></p>\r\n\r\n<p>- Cơ hội gắn b&oacute; l&agrave;m việc l&acirc;u d&agrave;i v&agrave; thăng tiến với c&ocirc;ng ty.</p>\r\n\r\n<p>- M&ocirc;i trường th&acirc;n thiện, h&ograve;a đồng, thoải m&aacute;i, hiện đại.</p>\r\n\r\n<p>- Tăng lương theo th&acirc;m ni&ecirc;n v&agrave; năng lực</p>\r\n\r\n<p>- Phụ cấp theo chế độ c&ocirc;ng ty (Gửi xe, Ăn uống, Lễ tết....)</p>\r\n\r\n<p>- Hưởng đầy đủ c&aacute;c ch&iacute;nh s&aacute;ch theo luật.</p>\r\n\r\n<p>- Được hỗ trợ đ&agrave;o tạo về chuy&ecirc;n m&ocirc;n kỹ năng khi l&agrave;m việc.</p>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<h3>Địa điểm l&agrave;m việc</h3>\r\n\r\n<p>- Hồ Ch&iacute; Minh: 18Bis/13 Nguyễn Thị Minh Khai, Quận 1</p>\r\n\r\n<p>- Hồ Ch&iacute; Minh: 30/11 Mạc Đỉnh Chi, Quận 1</p>\r\n\r\n<p>- Hồ Ch&iacute; Minh: 12 Đặng Trần C&ocirc;n, Quận 1</p>\r\n\r\n<h3>Thời gian l&agrave;m việc</h3>\r\n\r\n<p>Thời gian l&agrave;m việc: 01 th&aacute;ng OFF 4 ng&agrave;y kh&ocirc;ng rơi v&agrave;o Thứ 7 v&agrave; Chủ nhật<br />\r\nCa l&agrave;m việc: 6 ng&agrave;y/ tuần chia theo như sau<br />\r\n1 ng&agrave;y ca 10:00-19:00<br />\r\n1 ng&agrave;y ca 11:30 - 20:30<br />\r\n4 ng&agrave;y ca 13:00-22:00</p>\r\n', '2024-03-12', '2026-07-05', 'Female', 2, 1, 7, 1, 1, 4, 1, 0, 1),
(28, 40, 'nhan vien ', 'aaa', '2024-05-15', '2024-05-18', 'nam', 3, 1, 6, 1, 1, 4, 1, 0, 0),
(32, 42, 'Bai dang cong ty Thanh Tu', 'aaaa', '2024-05-17', '2024-05-17', 'Nam', 1, 1, 6, 1, 1, 4, 1, 0, 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `postingspayment`
--

CREATE TABLE `postingspayment` (
  `id` int(11) NOT NULL,
  `postings_id` int(11) NOT NULL,
  `cost` double NOT NULL,
  `duration` int(11) NOT NULL,
  `status` tinyint(1) NOT NULL,
  `created` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Đang đổ dữ liệu cho bảng `postingspayment`
--

INSERT INTO `postingspayment` (`id`, `postings_id`, `cost`, `duration`, `status`, `created`) VALUES
(9, 12, 3, 0, 0, '2024-05-14'),
(10, 12, 4, 0, 0, '2024-05-14'),
(11, 12, 56, 0, 0, '2024-05-14'),
(12, 12, 24, 0, 0, '2024-05-14'),
(13, 12, 25, 0, 0, '2024-05-14'),
(14, 12, 26, 1, 0, '2024-05-14'),
(32, 32, 50000, 1, 0, '2024-05-17');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `rank`
--

CREATE TABLE `rank` (
  `id` int(11) NOT NULL,
  `name` varchar(250) NOT NULL,
  `status` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `rank`
--

INSERT INTO `rank` (`id`, `name`, `status`) VALUES
(1, 'Nhân viên', 1),
(3, 'Quản Lý / Giám sát', 1),
(4, 'Trưởng nhóm', 1),
(5, 'Trưởng / Phó phòng', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `seeker`
--

CREATE TABLE `seeker` (
  `id` int(11) NOT NULL,
  `account_id` int(11) NOT NULL,
  `fullname` varchar(250) DEFAULT NULL,
  `phone` varchar(250) DEFAULT NULL,
  `description` text DEFAULT NULL,
  `cv_information` varchar(250) DEFAULT NULL,
  `status` tinyint(1) DEFAULT NULL,
  `avatar` varchar(250) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `seeker`
--

INSERT INTO `seeker` (`id`, `account_id`, `fullname`, `phone`, `description`, `cv_information`, `status`, `avatar`) VALUES
(1, 2, 'Lê Thanh Tú', '0948563963', '', 'cvThanhTu.pdf', 1, '1.jpeg'),
(4, 7, 'Trần Tấn Lộc', '0889999999', 'abc', 'cvThanhTu.pdf', 1, '1.jpeg'),
(7, 23, 'Lê Thanh Tú', '0948502190', NULL, 'cvThanhTu.pdf', 1, '1.jpeg');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `transaction_history`
--

CREATE TABLE `transaction_history` (
  `id` int(11) NOT NULL,
  `account_id` int(11) NOT NULL,
  `total` double NOT NULL,
  `created` date NOT NULL,
  `trading_code` int(11) NOT NULL,
  `status` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `transaction_history`
--

INSERT INTO `transaction_history` (`id`, `account_id`, `total`, `created`, `trading_code`, `status`) VALUES
(1, 2, 11, '2022-11-16', 1, 0),
(2, 2, 500000, '2024-05-10', 14404920, 1),
(3, 18, 500000, '2024-05-15', 14415806, 1),
(4, 14, 500000, '2024-05-15', 14415888, 1),
(5, 19, 500000, '2024-05-15', 14416057, 1),
(6, 24, 500000, '2024-05-17', 14418932, 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `type`
--

CREATE TABLE `type` (
  `id` int(11) NOT NULL,
  `name` varchar(250) NOT NULL,
  `status` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `type`
--

INSERT INTO `type` (`id`, `name`, `status`) VALUES
(4, 'Toàn thời gian', 1),
(5, 'Bán thời gian', 1),
(7, 'Thực tập', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `type_account`
--

CREATE TABLE `type_account` (
  `id` int(11) NOT NULL,
  `name` varchar(250) NOT NULL,
  `status` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `type_account`
--

INSERT INTO `type_account` (`id`, `name`, `status`) VALUES
(1, 'ROLE_SEEKER', 0),
(2, 'ROLE_EMPLOYER', 0),
(3, 'ROLE_ADMIN', 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `wage`
--

CREATE TABLE `wage` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `min` int(11) NOT NULL,
  `max` int(11) NOT NULL,
  `status` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `wage`
--

INSERT INTO `wage` (`id`, `name`, `min`, `max`, `status`) VALUES
(1, 'Dưới 10 triệu', 1, 10, 1),
(2, '10 - 15 triệu', 10, 15, 1),
(3, '15 - 20 triệu', 15, 20, 1),
(4, '20 - 25 triệu', 20, 25, 1),
(5, '25 - 30 triệu', 25, 30, 1),
(6, '30 - 50 triệu', 30, 50, 1),
(7, 'Trên 50 triệu', 50, 100, 1);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `account`
--
ALTER TABLE `account`
  ADD PRIMARY KEY (`id`),
  ADD KEY `type_account` (`type_account`);

--
-- Chỉ mục cho bảng `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id`),
  ADD KEY `account_id` (`account_id`);

--
-- Chỉ mục cho bảng `application_history`
--
ALTER TABLE `application_history`
  ADD PRIMARY KEY (`id`),
  ADD KEY `postings_id` (`postings_id`),
  ADD KEY `seeker_id` (`seeker_id`);

--
-- Chỉ mục cho bảng `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`id`),
  ADD KEY `parent_id` (`parent_id`);

--
-- Chỉ mục cho bảng `check_news`
--
ALTER TABLE `check_news`
  ADD PRIMARY KEY (`id`),
  ADD KEY `postings_id` (`postings_id`),
  ADD KEY `seekers_id` (`seekers_id`);

--
-- Chỉ mục cho bảng `employer`
--
ALTER TABLE `employer`
  ADD PRIMARY KEY (`id`),
  ADD KEY `account_id` (`account_id`);

--
-- Chỉ mục cho bảng `experience`
--
ALTER TABLE `experience`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `follow`
--
ALTER TABLE `follow`
  ADD PRIMARY KEY (`id`),
  ADD KEY `employer_id` (`employer_id`),
  ADD KEY `seeker_id` (`seeker_id`);

--
-- Chỉ mục cho bảng `keyword`
--
ALTER TABLE `keyword`
  ADD PRIMARY KEY (`id`),
  ADD KEY `category_id` (`category_id`);

--
-- Chỉ mục cho bảng `local`
--
ALTER TABLE `local`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `payment`
--
ALTER TABLE `payment`
  ADD PRIMARY KEY (`id`),
  ADD KEY `postings_id` (`postings_id`);

--
-- Chỉ mục cho bảng `postings`
--
ALTER TABLE `postings`
  ADD PRIMARY KEY (`id`),
  ADD KEY `employer_id` (`employer_id`),
  ADD KEY `experience_id` (`experience_id`),
  ADD KEY `local_id` (`local_id`),
  ADD KEY `rank_id` (`rank_id`),
  ADD KEY `type_id` (`type_id`),
  ADD KEY `wage_id` (`wage_id`),
  ADD KEY `category_id` (`category_id`);

--
-- Chỉ mục cho bảng `postingspayment`
--
ALTER TABLE `postingspayment`
  ADD PRIMARY KEY (`id`),
  ADD KEY `postings_id` (`postings_id`);

--
-- Chỉ mục cho bảng `rank`
--
ALTER TABLE `rank`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `seeker`
--
ALTER TABLE `seeker`
  ADD PRIMARY KEY (`id`),
  ADD KEY `account_id` (`account_id`);

--
-- Chỉ mục cho bảng `transaction_history`
--
ALTER TABLE `transaction_history`
  ADD PRIMARY KEY (`id`),
  ADD KEY `account_id` (`account_id`);

--
-- Chỉ mục cho bảng `type`
--
ALTER TABLE `type`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `type_account`
--
ALTER TABLE `type_account`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `wage`
--
ALTER TABLE `wage`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `account`
--
ALTER TABLE `account`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT cho bảng `admin`
--
ALTER TABLE `admin`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT cho bảng `application_history`
--
ALTER TABLE `application_history`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT cho bảng `category`
--
ALTER TABLE `category`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=46;

--
-- AUTO_INCREMENT cho bảng `check_news`
--
ALTER TABLE `check_news`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT cho bảng `employer`
--
ALTER TABLE `employer`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=43;

--
-- AUTO_INCREMENT cho bảng `experience`
--
ALTER TABLE `experience`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT cho bảng `follow`
--
ALTER TABLE `follow`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT cho bảng `keyword`
--
ALTER TABLE `keyword`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT cho bảng `local`
--
ALTER TABLE `local`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT cho bảng `payment`
--
ALTER TABLE `payment`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT cho bảng `postings`
--
ALTER TABLE `postings`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;

--
-- AUTO_INCREMENT cho bảng `postingspayment`
--
ALTER TABLE `postingspayment`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;

--
-- AUTO_INCREMENT cho bảng `rank`
--
ALTER TABLE `rank`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT cho bảng `seeker`
--
ALTER TABLE `seeker`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT cho bảng `transaction_history`
--
ALTER TABLE `transaction_history`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT cho bảng `type`
--
ALTER TABLE `type`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT cho bảng `type_account`
--
ALTER TABLE `type_account`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT cho bảng `wage`
--
ALTER TABLE `wage`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `account`
--
ALTER TABLE `account`
  ADD CONSTRAINT `account_ibfk_1` FOREIGN KEY (`type_account`) REFERENCES `type_account` (`id`);

--
-- Các ràng buộc cho bảng `admin`
--
ALTER TABLE `admin`
  ADD CONSTRAINT `admin_ibfk_1` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`);

--
-- Các ràng buộc cho bảng `application_history`
--
ALTER TABLE `application_history`
  ADD CONSTRAINT `application_history_ibfk_1` FOREIGN KEY (`postings_id`) REFERENCES `postings` (`id`),
  ADD CONSTRAINT `application_history_ibfk_2` FOREIGN KEY (`seeker_id`) REFERENCES `seeker` (`id`);

--
-- Các ràng buộc cho bảng `check_news`
--
ALTER TABLE `check_news`
  ADD CONSTRAINT `check_news_ibfk_1` FOREIGN KEY (`postings_id`) REFERENCES `postings` (`id`),
  ADD CONSTRAINT `check_news_ibfk_2` FOREIGN KEY (`seekers_id`) REFERENCES `seeker` (`id`);

--
-- Các ràng buộc cho bảng `employer`
--
ALTER TABLE `employer`
  ADD CONSTRAINT `employer_ibfk_1` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`);

--
-- Các ràng buộc cho bảng `follow`
--
ALTER TABLE `follow`
  ADD CONSTRAINT `follow_ibfk_1` FOREIGN KEY (`employer_id`) REFERENCES `employer` (`id`),
  ADD CONSTRAINT `follow_ibfk_2` FOREIGN KEY (`seeker_id`) REFERENCES `seeker` (`id`);

--
-- Các ràng buộc cho bảng `keyword`
--
ALTER TABLE `keyword`
  ADD CONSTRAINT `keyword_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`);

--
-- Các ràng buộc cho bảng `payment`
--
ALTER TABLE `payment`
  ADD CONSTRAINT `payment_ibfk_1` FOREIGN KEY (`postings_id`) REFERENCES `postings` (`id`);

--
-- Các ràng buộc cho bảng `postings`
--
ALTER TABLE `postings`
  ADD CONSTRAINT `postings_ibfk_1` FOREIGN KEY (`employer_id`) REFERENCES `employer` (`id`),
  ADD CONSTRAINT `postings_ibfk_2` FOREIGN KEY (`experience_id`) REFERENCES `experience` (`id`),
  ADD CONSTRAINT `postings_ibfk_3` FOREIGN KEY (`local_id`) REFERENCES `local` (`id`),
  ADD CONSTRAINT `postings_ibfk_4` FOREIGN KEY (`rank_id`) REFERENCES `rank` (`id`),
  ADD CONSTRAINT `postings_ibfk_6` FOREIGN KEY (`type_id`) REFERENCES `type` (`id`),
  ADD CONSTRAINT `postings_ibfk_7` FOREIGN KEY (`wage_id`) REFERENCES `wage` (`id`),
  ADD CONSTRAINT `postings_ibfk_8` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`);

--
-- Các ràng buộc cho bảng `postingspayment`
--
ALTER TABLE `postingspayment`
  ADD CONSTRAINT `postingspayment_ibfk_1` FOREIGN KEY (`postings_id`) REFERENCES `postings` (`id`);

--
-- Các ràng buộc cho bảng `seeker`
--
ALTER TABLE `seeker`
  ADD CONSTRAINT `seeker_ibfk_1` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`);

--
-- Các ràng buộc cho bảng `transaction_history`
--
ALTER TABLE `transaction_history`
  ADD CONSTRAINT `transaction_history_ibfk_1` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
