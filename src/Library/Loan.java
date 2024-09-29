package Library;

import java.util.Date;

public class Loan {
    private int loanId;
    private int bookId;
    private int memberId;
    private Date loanDate;
    private Date dueDate;

    public Loan(int bookId, int memberId, Date loanDate, Date dueDate) {
        this.bookId = bookId;
        this.memberId = memberId;
        this.loanDate = loanDate;
        this.dueDate = dueDate;
    }

    public Loan(int loanId, int bookId, int memberId, Date loanDate, Date dueDate) {
        this.loanId = loanId;
        this.bookId = bookId;
        this.memberId = memberId;
        this.loanDate = loanDate;
        this.dueDate = dueDate;
    }

    public int getLoanId() {
        return loanId;
    }

    public int getBookId() {
        return bookId;
    }

    public int getMemberId() {
        return memberId;
    }

    public Date getLoanDate() {
        return loanDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setLoanId(int loanId) {
        this.loanId = loanId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public void setLoanDate(Date loanDate) {
        this.loanDate = loanDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return "Loan ID: " + loanId +
                ", Book ID: " + bookId +
                ", Member ID: " + memberId +
                ", Loan Date: " + loanDate +
                ", Due Date: " + dueDate;
    }
}

