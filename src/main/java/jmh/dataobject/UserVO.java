package jmh.dataobject;

/**
 * @author fanwh
 * @version v1.0
 * @decription
 * @create on 2017/12/5 13:27
 */
public class UserVO {
    private String name;
    private String nickName;
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
