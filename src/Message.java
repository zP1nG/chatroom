import java.io.Serializable;

public class Message implements Serializable{
	private static final long serialVersionUID = 2353758703621168451L;
	private int type;//消息类型
	private String info;//消息内容
	public Message() {
		;
	}
	public Message(int type, String info) {
		super();
		this.type = type;
		this.info = info;
	}
	@Override
	public String toString() {
		return "Message { + type=" + type + ", info=" + info + "}";
	}
	public int getType() {
        return type;
    }
 
    public void setType(int type) {
        this.type = type;
    }
 
    public String getInfo() {
        return info;
    }
 
    public void setInfo(String info) {
        this.info = info;
    }

}
