import { makeStyles } from "@mui/styles";


const authenticationStyle = makeStyles((theme) => ({

  authCard : {

    backgroundColor: theme.custom.subBackground,
    
    "& > .css-1lt5qva-MuiCardContent-root" : {
      padding : "40px 30px",
      paddingBottom: "40px"
    },
    "& .css-1lt5qva-MuiCardContent-root:last-child" : {
      paddingBottom: "40px"
    }

  },

  authButtonStyle : {
    padding: "10px 0px", 
    fontSize: "15px"
  }
}));

export {authenticationStyle};