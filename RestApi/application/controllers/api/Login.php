<?php

defined('BASEPATH') OR exit('No direct script access allowed');

require APPPATH . '/libraries/REST_Controller.php';
use Restserver\Libraries\REST_Controller;

class Login extends REST_Controller {

    function __construct($config = 'rest') {
        parent::__construct($config);
        $this->load->model('api/mlogin', 'a');
    }

    //Login
    public function index_post() {

        # Form Validation
        $this->form_validation->set_rules('username', 'username', 'trim|required');
        $this->form_validation->set_rules('password', 'password', 'trim|required');
        if ($this->form_validation->run() == FALSE) {
            // Form Validation
            $message = array(
                'status' => false,
                'error' => $this->form_validation->error_array(),
                'message' => validation_errors()
            );
            $this->response($message, REST_Controller::HTTP_NOT_FOUND);
        } else {
            // Load Login Function
            $output = $this->a->akun_login($this->input->post('username'), $this->input->post('password'));
            if(!empty($output) AND $output != FALSE) {

                // Load Authorization Token Library
                $this->load->library('Authorization_Token');

                // Generate Token
                $token_data['kd_pengguna'] = $output->kd_pengguna;
                $token_data['NIK_pengguna'] = $output->NIK_pengguna;
                $token_data['nama_pengguna'] = $output->nama_pengguna;
                $token_data['alamat_pengguna'] = $output->alamat_pengguna;
                $token_data['no_pengguna'] = $output->no_pengguna;
                $token_data['email'] = $output->email;
                $token_data['foto_ktp'] = $output->foto_ktp;
                $token_data['foto_diri_dan_ktp'] = $output->foto_diri_dan_ktp;
                $token_data['foto_profil'] = $output->foto_profil;
                $token_data['username'] = $output->username;
                $token_data['password'] = $output->password;
                $token_data['role'] = $output->role;

                $akun_token = $this->authorization_token->generateToken($token_data);

                $return_data = [
                    'kd_pengguna' => $output->kd_pengguna,
                    'NIK_pengguna' => $output->NIK_pengguna,
                    'nama_pengguna' => $output->nama_pengguna,
                    'alamat_pengguna' => $output->alamat_pengguna,
                    'no_pengguna' => $output->no_pengguna,
                    'email' => $output->email,
                    'foto_ktp' => $output->foto_ktp,
                    'foto_diri_dan_ktp' => $output->foto_diri_dan_ktp,
                    'foto_profil' => $output->foto_profil,
                    'username' => $output->username,
                    'password' => $output->password,
                    'role' => $output->role,
                    'token' => $akun_token,
                    'pesan' => "Selamat Datang di PAKTI",
                ];

                // Login Success
                $message = [
                    'status' => TRUE,
                    'data' => $return_data,
                    'message' => "Selamat Datang di PAKTI"
                ];
                $this->response($message, REST_Controller::HTTP_OK);
            } else {
                // LoginError
                $message = [
                    'status' => FALSE,
                    'message' => "Username atau Password Salah"
                ];
                $this->response($message, REST_Controller::HTTP_NOT_FOUND);
            }
        }
    }
}