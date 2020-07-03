<?php

defined('BASEPATH') or exit('No direct script access allowed');

require APPPATH . '/libraries/REST_Controller.php';

use Restserver\Libraries\REST_Controller;

class Register extends REST_Controller {


    function __construct($config = 'rest')
    {
        parent::__construct($config);
        $this->load->database();
    }
    function index_post()
    {
        $data = array(
            'NIK_pengguna'          => $this->post('NIK_pengguna'),
            'nama_pengguna'         => $this->post('nama_pengguna'),
            'alamat_pengguna'       => $this->post('alamat_pengguna'),
            'no_pengguna'           => $this->post('no_pengguna'),
            'email'                 => $this->post('email'),
            'username'              => $this->post('username'),
            'password'              => $this->post('password'),
            'role'                  => 'penyewa'

        );
        $insert = $this->db->insert('tabel_pengguna', $data);
        if ($insert) {
            $this->response($data, 200);
        } else {
            $this->response(array('status' => 'fail', 502));
        }
    }
}
?>