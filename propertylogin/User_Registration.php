<?php
if($_SERVER['REQUEST_METHOD']=='POST'){

include 'DatabaseConfig.php';

 $con = mysqli_connect($HostName,$HostUser,$HostPass,$DatabaseName);

 $NIK = $_POST['nik_penyewa'];
 $Nama = $_POST['nama_penyewa'];
 $Alamat = $_POST['alamat_penyewa'];
 $Email = $_POST['email'];
 $Username = $_POST['username'];
 $Password = $_POST['password'];

 $CheckSQL = "SELECT * FROM tabel_penyewa WHERE nik_penyewa='$NIK'";
 
 $check = mysqli_fetch_array(mysqli_query($con,$CheckSQL));
 
 if(isset($check)){

 echo 'Email Already Exist, Please Enter Another Email.';

 }
else{ 
$Sql_Query = "INSERT INTO tabel_penyewa (nik_penyewa,nama_penyewa,alamat_penyewa,email,username, password) values ('$NIK','$Nama','$Alamat','$Email','$Username','$Password')";

 if(mysqli_query($con,$Sql_Query))
{
 echo 'User Registration Successfully';
}
else
{
 echo 'Something went wrong';
 }
 }
  mysqli_close($con);
}

?>