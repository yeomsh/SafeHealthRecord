package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import Blockchain.BCManager;
import DataClass.Contract;
import org.json.simple.JSONObject;

public class ContractGUI extends JFrame {
   Contract contract;

   JScrollPane scroll;
   JPanel panel, paButton, prescripPl;

   JLabel titleLb = new JLabel("처방전");
   JLabel medicineLb, doctor1Lb, doctor2Lb, doctor3Lb, usageLb;
   JLabel oSignTitleLb, wSignTitleLb;
   JLabel oSign1Lb, oSign2Lb, oSign3Lb, oSign4Lb;
   JLabel wSign1Lb, wSign2Lb;
   JLabel contractDateLb;
   JLabel noticeTxt;

   MyCalendar contractDatePl;

   JTextArea medicineArea;
   JTextField doctor1Txt, doctor2Txt, usageTxt, doctor3Txt;
   JTextField oSign1Txt, oSign2Txt, oSign3Txt, oSign4Txt;
   JTextField wSign1Txt, wSign2Txt;

   JRadioButton[] prescripCb = new JRadioButton[5];
   ButtonGroup prescripG;
   String[] prescripString = { "건강보험", "의료급여", "산재보험","자동차보험","기타"};

   public  JButton btnSubmit, btnCancel, btnAbort;

   //테스트용 변수
//   public int testStep = 0; //스탭 저장 변수
//   public JSONObject jsonObject = new JSONObject(); // 계약서 json으로 저장하는 변수

   public ContractGUI(BCManager manager) throws Exception { //BCManager 에서 호출됨
      this();
      contract = manager.contract;
      setPanel();
      btnSubmit.addActionListener(manager.eventHandler);
      btnAbort.addActionListener(manager.eventHandler);
   }

   public ContractGUI(JSONObject contract) throws Exception { //BCManager 에서 호출됨
      this();
      showPannel(contract);
   }

   public ContractGUI() {

      super("처방전 작성 화면");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setLayout(new BorderLayout());
      makePanel();
      setResizable(false);
      setVisible(false);

      //테스트 용 코드
//      setPanel();
//      btnSubmit.addActionListener(new ActionListener() {
//         @Override
//         public void actionPerformed(ActionEvent e) {
//            jsonObject = getStepContract();
//            testStep++;
//            setVisible(false);
//            System.out.println("testStep : " + testStep);
//            setPanel();
//         }
//      });

      btnCancel.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(null,"진행하던 작업을 취소합니다.","Message",JOptionPane.INFORMATION_MESSAGE);
            setVisible(false);
            System.out.println("취소");
         }
      });

   }

   public void setPanel(){
      //contract.step -> testStep (테스트용)

      System.out.println("contract gui: set panel , step: "+ contract.step/*testStep*/);

      switch (contract.step/*testStep*/+1){ //step에 저장된 값은 이전 단계에서 작성된 것이  +1
         case 1:
            setStep1Contract();
            break;
         case 2:
            setStep2Contract(contract.fileData/*jsonObject*/);
            break;
         case 3:
            setStep3Contract(contract.fileData/*jsonObject*/);
            break;
         case 4:
            setStep4Contract(contract.fileData/*jsonObject*/);
            break;
         case 5:
            setStep5Contract(contract.fileData/*jsonObject*/);
         default:
            break;
      }
   }

   public void showPannel(JSONObject data) throws Exception {
      setContractField(data);
      setVisiableAllFalse();
      btnSubmit.setText("확인");
      btnAbort.setEnabled(false);
      btnCancel.setText("닫기");
      setVisible(true);
      btnSubmit.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(null, "확인", "Message", JOptionPane.INFORMATION_MESSAGE);
            setVisible(false);
         }
      });
   }

   // step1 의료진이 작성
   public void setStep1Contract() {
      setVisiableAllFalse();
      titleLb.setEnabled(true);
      prescripPl.setEnabled(true);
      for (JRadioButton btn : prescripCb) {
         btn.setEnabled(true);
      }
      contractDateLb.setEnabled(true);
      oSignTitleLb.setEnabled(true);
      oSign1Lb.setEnabled(true);
      oSign1Txt.setEnabled(true);
      oSign2Lb.setEnabled(true);
      oSign2Txt.setEnabled(true);
      oSign3Lb.setEnabled(true);
      oSign3Txt.setEnabled(true);
      oSign4Lb.setEnabled(true);
      oSign4Txt.setEnabled(true);
      doctor1Lb.setEnabled(true);
      doctor2Lb.setEnabled(true);
      doctor3Lb.setEnabled(true);
      doctor1Txt.setEnabled(true);
      doctor2Txt.setEnabled(true);
      doctor3Txt.setEnabled(true);
      medicineLb.setEnabled(true);
      medicineArea.setEnabled(true);
      usageLb.setEnabled(true);
      usageTxt.setEnabled(true);
      btnSubmit.setEnabled(true);
      btnAbort.setEnabled(true);
      noticeTxt.setEnabled(true);
      setVisible(true);
   }

   // step2 환자분이 작성
   public void setStep2Contract(JSONObject json) {
      setContractField(json);
      setVisiableAllFalse();
      titleLb.setEnabled(true);
      wSignTitleLb.setEnabled(true);
      wSign1Lb.setEnabled(true);
      wSign2Lb.setEnabled(true);
      wSign1Txt.setEnabled(true);
      wSign2Txt.setEnabled(true);
      btnSubmit.setEnabled(true);
      btnAbort.setEnabled(true);
      setVisible(true);
   }

   // step3 의료진 서명
   public void setStep3Contract(JSONObject json) {
      setContractField(json);
      setVisiableAllFalse();
      titleLb.setEnabled(true);
      btnSubmit.setText("서명하기");
      btnSubmit.setEnabled(true);
      btnAbort.setEnabled(true);
      setVisible(true);
   }

   // step4 환자분 서명
   public void setStep4Contract(JSONObject json) {
      setContractField(json);
      setVisiableAllFalse();
      titleLb.setEnabled(true);
      btnSubmit.setText("서명하기");
      btnSubmit.setEnabled(true);
      btnAbort.setEnabled(true);
      setVisible(true);
   }

   //step5 블록체인
   public void setStep5Contract(JSONObject json) { //점주가 근로자의 서명이 붙은 파일을 최종적으로 검증하는 창
      setContractField(json);
      setVisiableAllFalse();
      btnSubmit.setText("블록체인");
      btnSubmit.setEnabled(true);
      btnAbort.setEnabled(true);
      setVisible(true);
   }

   public void setContractField(JSONObject data) {
      System.out.println("setcontractfield : \n"+data);

      for (int i = 0; i < 5; i++) {
         prescripCb[i].setSelected((boolean)(((JSONObject)data.get("prescrip")).get(prescripString[i])));
      }
      contractDatePl.setSelectDate((JSONObject) data.get("contractDate"));

      wSign1Txt.setText((String) ((JSONObject) data.get("wSign")).get("wSign1"));
      wSign2Txt.setText((String) ((JSONObject) data.get("wSign")).get("wSign2"));

      oSign1Txt.setText((String) ((JSONObject) data.get("oSign")).get("oSign1"));
      oSign2Txt.setText((String) ((JSONObject) data.get("oSign")).get("oSign2"));
      oSign3Txt.setText((String) ((JSONObject) data.get("oSign")).get("oSign3"));
      oSign4Txt.setText((String) ((JSONObject) data.get("oSign")).get("oSign4"));

      doctor1Txt.setText((String) data.get("doctor1"));
      doctor2Txt.setText((String) data.get("doctor2"));
      doctor3Txt.setText((String) data.get("doctor3"));

      medicineArea.setText((String)data.get("medicine"));
      usageTxt.setText((String) data.get("usage"));

      setVisible(true);
   }

   public JSONObject getStepContract() {
      JSONObject data = new JSONObject();
      JSONObject wSign = new JSONObject();
      JSONObject oSign = new JSONObject();
      JSONObject ox = new JSONObject();

      for (int i = 0; i < 5; i++) {
         ox.put(prescripString[i], prescripCb[i].isSelected());
      }
      data.put("prescrip", ox);

      data.put("contractDate", contractDatePl.getSelectDate());

      wSign.put("wSign1", wSign1Txt.getText());
      wSign.put("wSign2", wSign2Txt.getText());
      data.put("wSign", wSign);

      oSign.put("oSign1", oSign1Txt.getText());
      oSign.put("oSign2", oSign2Txt.getText());
      oSign.put("oSign3", oSign3Txt.getText());
      oSign.put("oSign4", oSign4Txt.getText());
      data.put("oSign", oSign);

      data.put("doctor1", doctor1Txt.getText());
      data.put("doctor2", doctor2Txt.getText());
      data.put("doctor3", doctor3Txt.getText());

      data.put("medicine",medicineArea.getText());
      data.put("usage", usageTxt.getText());

      System.out.println(data);

      return data;
   }

   public void addPanelToContent(){
      panel.add(titleLb);
      panel.add(prescripPl);
      panel.add(contractDateLb);
      panel.add(contractDatePl);

      panel.add(wSignTitleLb);
      panel.add(wSign1Lb);
      panel.add(wSign1Txt);
      panel.add(wSign2Lb);
      panel.add(wSign2Txt);

      panel.add(oSignTitleLb);
      panel.add(oSign1Lb);
      panel.add(oSign1Txt);
      panel.add(oSign2Lb);
      panel.add(oSign2Txt);
      panel.add(oSign3Lb);
      panel.add(oSign3Txt);
      panel.add(oSign4Lb);
      panel.add(oSign4Txt);

      panel.add(doctor1Lb);
      panel.add(doctor1Txt);
      panel.add(doctor2Lb);
      panel.add(doctor2Txt);
      panel.add(doctor3Lb);
      panel.add(doctor3Txt);

      panel.add(medicineLb);
      panel.add(medicineArea);

      panel.add(usageLb);
      panel.add(usageTxt);
      panel.add(noticeTxt);

      panel.add(paButton);
   }

   public void setVisiableAllFalse(){
      titleLb.setEnabled(false);
      prescripPl.setEnabled(false);
      contractDateLb.setEnabled(false);
      contractDatePl.setEnabled(false);

      wSignTitleLb.setEnabled(false);
      wSign1Lb.setEnabled(false);
      wSign1Txt.setEnabled(false);
      wSign2Lb.setEnabled(false);
      wSign2Txt.setEnabled(false);

      oSignTitleLb.setEnabled(false);
      oSign1Lb.setEnabled(false);
      oSign1Txt.setEnabled(false);
      oSign2Lb.setEnabled(false);
      oSign2Txt.setEnabled(false);
      oSign3Lb.setEnabled(false);
      oSign3Txt.setEnabled(false);
      oSign4Lb.setEnabled(false);
      oSign4Txt.setEnabled(false);

      doctor1Lb.setEnabled(false);
      doctor1Txt.setEnabled(false);
      doctor2Lb.setEnabled(false);
      doctor2Txt.setEnabled(false);
      doctor3Lb.setEnabled(false);
      doctor3Txt.setEnabled(false);

      medicineLb.setEnabled(false);
      medicineArea.setEnabled(false);

      usageLb.setEnabled(false);
      panel.setEnabled(false);
      panel.setEnabled(false);

      for (JRadioButton btn : prescripCb) {
         btn.setEnabled(false);
      }
   }

   public void setBound(){
      titleLb.setBounds(200, 10, 400, 20);
      prescripPl.setBounds(20, 40, 400, 40);
      contractDateLb.setBounds(20, 85, 120, 20);
      contractDatePl.setBounds(130, 80, 200, 40);
      wSignTitleLb.setBounds(20, 110, 100, 20);
      wSign1Lb.setBounds(50, 140, 80, 20);
      wSign1Txt.setBounds(150, 140, 200, 20);
      wSign2Lb.setBounds(50, 170, 120, 20);
      wSign2Txt.setBounds(150, 170, 200, 20);

      oSignTitleLb.setBounds(20, 200, 100, 20);
      oSign1Lb.setBounds(50, 230, 80, 20);
      oSign1Txt.setBounds(150, 230, 200, 20);
      oSign2Lb.setBounds(50, 260, 120, 20);
      oSign2Txt.setBounds(150, 260, 200, 20);
      oSign3Lb.setBounds(50, 290, 80, 20);
      oSign3Txt.setBounds(150, 290, 200, 20);
      oSign4Lb.setBounds(50, 320, 120, 20);
      oSign4Txt.setBounds(150, 320, 200, 20);

      doctor1Lb.setBounds(20, 380, 130, 20);
      doctor1Txt.setBounds(150, 380, 200, 20);
      doctor2Lb.setBounds(20, 410, 120, 20);
      doctor2Txt.setBounds(150, 410, 200, 20);
      doctor3Lb.setBounds(20, 440, 120, 20);
      doctor3Txt.setBounds(150, 440, 200, 20);

      medicineLb.setBounds(20,500,400,20);
      medicineArea.setBounds(20,530,420,100);

      usageLb.setBounds(20, 660, 150, 20);
      usageTxt.setBounds(170, 660, 30, 20);
      noticeTxt.setBounds(20,680,300,20);

      paButton.setBounds(50, 730, 370, 150);
   }

   public void setPanelAndPaButton(){
      panel = new JPanel();
      paButton = new JPanel();

      panel.setLayout(null);
      panel.setBackground(Color.white);
      paButton.setBackground(Color.white);
      panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
      panel.setPreferredSize(new Dimension(500, 850));

      scroll = new JScrollPane(panel);
      add(scroll, BorderLayout.CENTER);
      setSize(500, 850);

      btnCancel = new JButton("취소");
      btnSubmit = new JButton("작성완료");
      btnAbort = new JButton("폐기");
      btnCancel.setSize(150, 40);
      btnSubmit.setSize(150, 40);
      btnAbort.setSize(150,40);

      paButton.add(btnSubmit);
      paButton.add(btnCancel);
      paButton.add(btnAbort);
   }

   public void makePanel(){
      setPanelAndPaButton();
      titleLb.setFont(new Font(null, Font.BOLD, 15));
      prescripPl = new JPanel(new FlowLayout(FlowLayout.LEFT));
      prescripPl.setBackground(Color.white);
      prescripG = new ButtonGroup();

      for (int i = 0; i < 5; i++) {
         prescripCb[i] = new JRadioButton(prescripString[i]);
         prescripCb[i].setBackground(Color.white);
         prescripPl.add(prescripCb[i]);
         prescripG.add(prescripCb[i]);
      }

      contractDateLb = new JLabel("교부년월일 및 번호");
      contractDatePl = new MyCalendar();

      wSignTitleLb = new JLabel("환자");
      wSign1Lb = new JLabel("성명");
      wSign2Lb = new JLabel("주민등록번호");
      wSign1Txt = new JTextField(20);
      wSign2Txt = new JTextField(20);

      oSignTitleLb = new JLabel("의료기관");
      oSign1Lb = new JLabel("명칭");
      oSign2Lb = new JLabel("전화번호");
      oSign3Lb = new JLabel("팩스번호");
      oSign4Lb = new JLabel("e-mail주소");
      oSign1Txt = new JTextField(20);
      oSign2Txt = new JTextField(20);
      oSign3Txt = new JTextField(20);
      oSign4Txt = new JTextField(20);

      doctor1Lb = new JLabel("처방 의료인의 성명");
      doctor1Txt = new JTextField(20);
      doctor2Lb = new JLabel("면허종별");
      doctor2Txt = new JTextField(20);
      doctor3Lb = new JLabel("면허번호");
      doctor3Txt = new JTextField(20);

      medicineLb = new JLabel("처방 의약품의 명칭/1회 투약량/1일투여횟수/총투약일수/용법");
      medicineArea = new JTextArea(10,10);
      medicineArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));

      usageLb = new JLabel("교부일로부터 사용기간");
      usageTxt = new JTextField(20);

      noticeTxt = new JLabel("사용기간 내에 약국에 제출하여야 합니다.");

      //의약품 조제내역은 일단 제외
      setBound();
      addPanelToContent();
   }

//   계약서 GUI 테스트 코드 -> 생성자에서 BTNSUBMIT활성화 후 setPanel() 테스트 코드로 변경
//   public static void main(String[] args){
//      ContractGUI c = new ContractGUI();
//   }

}