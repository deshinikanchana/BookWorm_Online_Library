package BookWorm.BO;

import BookWorm.BO.impl.*;

public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory(){

    }
    public static BOFactory getBoFactory(){
        return (boFactory==null)?boFactory=
                new BOFactory():boFactory;

    }
    public enum BOTypes{
        ADMINDB,ADMINLOG,ADMINNEW,ADMINSET,ALLTRANS,BOOKMANAGE,BRANCH,OWNTRANS,SEARCHBOOK,USERFORM,USERLOG,USERREG,USERSET
    }
    public SuperBO getBO(BOTypes boTypes){
        switch (boTypes){
            case ADMINDB:
                return new AdminDashBoardBOimpl();
            case ADMINLOG:
                return new AdminLoginBOimpl();
            case ADMINNEW:
                return new AdminNewAccBOimpl();
            case ADMINSET:
                return new AdminSettingBOimpl();
            case ALLTRANS:
                return new AllTransactionBOimpl();
            case BOOKMANAGE:
                return new BookManageBOimpl();
            case BRANCH:
                return new BranchesBOimpl();
            case OWNTRANS:
                return new OwnTransactionBOimpl();
            case SEARCHBOOK:
                return new SearchBookBOimpl();
            case USERFORM:
                return new UserFormBOimpl();
            case USERLOG:
                return new UserLoginBOimpl();
            case USERREG:
                return new UserRegBOimpl();
            case USERSET:
                return new UserSettingBOimpl();
            default:
                return null;
        }
    }
}
