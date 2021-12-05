public class Review {
		private int viewnum;
		private String star;
		private String writer;
		private String resname;
		private String content;
		
		public Review(int viewnum, String content, String star, String writer, String resname) {
			this.viewnum=viewnum;
			this.star=star;
			this.writer=writer;
			this.resname=resname;
			this.content=content;
		}
		public int get_viewnum() {
			return this.viewnum;
		}
		public String get_star() {
			return this.star;
		}
		public String get_writer() {
			return this.writer;
		}
		public String get_resname() {
			return this.resname;
		}
		public String get_content() {
			return this.content;
		}
	}