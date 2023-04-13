package hotel.management.v1.member.entity;

import lombok.Getter;

@Getter
public enum Level {
	BRONZE("BRONZE"),SILVER("SILVER"),GOLD("GOLD");
	private String korean;
	
	Level(String string) {
		this.korean=string;
	}
}
