<?php
class mlogin extends CI_Model{
    protected $tabel_pengguna = 'tabel_pengguna';
    
    public function akun_login($username, $password) 
    {
        $this->db->where('username', $username);
        $q = $this->db->get($this->tabel_pengguna);

        if($q->num_rows()) {
            $akun_pass = $q->row('password');
            if($password === $akun_pass) {
                return $q->row();
            }
            return FALSE;
        } else {
            return FALSE;
        }
    }
}   