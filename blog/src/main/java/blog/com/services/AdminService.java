package blog.com.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blog.com.models.dao.AdminDao;
import blog.com.models.entity.Admin;

@Service
public class AdminService {
	@Autowired
	private AdminDao adminDao;

	// 新しいadminを作成
	public boolean createAdmin(String adminEmail, String adminName, String password) {
		// もし、すでに同じメルアドレスのadminがnull場合、新しいadminを保存
		if (adminDao.findByAdminEmail(adminEmail) == null) {
			adminDao.save(new Admin(adminEmail, adminName, password));
			return true;
			// そうでない場合、falseに返す
		} else {
			return false;
		}
	}

	// ログインチェックを行う
	public Admin loginCheck(String adminEmail, String password) {
		// メルアドレスとパスワードに基づいてadminを検索
		Admin admin = adminDao.findByAdminEmailAndPassword(adminEmail, password);
		// もし、adminがnull場合、nullに返す
		if (admin == null) {
			return null;
			// そうでない場合、adminに返す
		} else {
			return admin;
		}
	}

}
