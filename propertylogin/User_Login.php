<?php
if($_SERVER['REQUEST_METHOD']=='POST'){

include 'DatabaseConfig.php';

 $con = mysqli_connect($HostName,$HostUser,$HostPass,$DatabaseName);

 $Username = $_POST['username'];
 $Password = $_POST['password'];

 $Sql_Query = "SELECT * FROM tabel_penyewa WHERE username='$Username' and password='$Password'";
 
 $check = mysqli_fetch_array(mysqli_query($con,$Sql_Query));
 
 if(isset($check)){

    echo "Data Matched";
}
else{
    echo "Invalid Username or Password Please Try Again !";
}
}
else{
    echo "Check Again";
}

?>