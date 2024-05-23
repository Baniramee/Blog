package blog.com.models.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.NonNull;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Admin {

	// admin_idの設定
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long adminId;

	@NonNull
	private String adminEmail;
	
	@NonNull
	private String adminName;
	
	@NonNull
	private String password;
}
