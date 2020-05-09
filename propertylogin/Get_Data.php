<?php 
 include 'DatabaseConfig.php';
 $con = mysqli_connect($HostName,$HostUser,$HostPass,$DatabaseName);
 
// Check connection
if(mysqli_connect_errno($con)) {
    die("Failed to connect to MySQL: " . mysqli_connect_error());
} 
 
// query the application data
$sql = "SELECT * FROM tabel_transaksi inner join tabel_properti on tabel_transaksi.nik_pemilik=tabel_properti.nik_pemilik";
$result = mysqli_query($con, $sql);
 
// an array to save the application data
$rows = array();
 
// iterate to query result and add every rows into array
while($row = mysqli_fetch_array($result, MYSQLI_ASSOC)) {
    $rows[] = $row; 
}
 
// close the database connection
mysqli_close($con);
 
// echo the application data in json format
echo json_encode($rows);