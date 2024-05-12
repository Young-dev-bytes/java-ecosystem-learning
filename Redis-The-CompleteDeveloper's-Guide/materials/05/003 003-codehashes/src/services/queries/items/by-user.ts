interface QueryOpts {
	page: number;
	perPage: number;
	sortBy: string;
	direction: string;
	tag: string;
}

export const itemsByUser = async (userId: string, opts: QueryOpts) => {};
