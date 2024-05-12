export interface Item {
	id: string;
	name: string;
	ownerId: string;
	imageUrl: string;
	description: string;
	createdAt: number;
	endingAt: number;
	views: number;
	likes: number;
	price: number;
	bids: number;
	highestBidUserId: string;
}

export interface CreateItemAttrs {
	name: string;
	imageUrl: string;
	description: string;
	duration: number;
}

export interface User {
	id: string;
	username: string;
	password: string;
}

export interface CreateUserDto {
	username: string;
	password: string;
}

export interface CreateBidAttrs {
	itemId: string;
	userId: string;
	amount: number;
	createdAt: number;
	itemEndingAt: number;
}

export interface Session {
	id: string;
	userId: string | null;
	username: string;
}

export interface CreateSessionDto {
	userId: string;
}
