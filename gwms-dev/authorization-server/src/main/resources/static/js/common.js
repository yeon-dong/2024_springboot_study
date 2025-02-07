document.addEventListener('DOMContentLoaded', () => {
  window.addEventListener('load', () => {
    console.log('load window');

    const inputs = document.querySelectorAll('.input-text');

    if (!inputs) return;

    inputs.forEach((input) => {
      const fieldContainer = input.closest('.field-container');
      const delBtn = fieldContainer.querySelector('.btn-delete');
      const viewBtn = fieldContainer.querySelector('.btn-view');
      let inputType = input.getAttribute('type');

      const updateVisibility = () => {
        const isVisible = input.value.length > 0 && !input.hasAttribute('readonly');
        delBtn.toggleAttribute('hidden', !isVisible);
        if(viewBtn){
          viewBtn.toggleAttribute('hidden', !isVisible);
        }
      };

      // 키보드 감지
      input.addEventListener('keyup', updateVisibility);

      // 초기 상태 설정
      updateVisibility();

      // Readonly일 경우 label 항상 hidden 처리
      if (input.hasAttribute('readonly')) {
        delBtn.setAttribute('hidden', '');
        if(viewBtn){
          viewBtn.setAttribute('hidden', '');
        }
      }

      // 삭제 버튼 클릭 이벤트
      delBtn.addEventListener('click', () => {
        input.value = '';
        delBtn.setAttribute('hidden', '');
        viewBtn.setAttribute('hidden', '');
      });

      // 패스워드 view 버튼 클릭 이벤트
      if(fieldContainer.classList.contains('input-password')){
        viewBtn.addEventListener('click', () => {
          if(inputType == 'password'){
            input.setAttribute('type', 'text');
            inputType = input.getAttribute('type');
          }else{
            input.setAttribute('type', 'password');
            inputType = input.getAttribute('type');
          }
        });
      }
    });
  });

  // scroll modal
  const modals = document.querySelectorAll('.modal');
  if(modals){
    modals.forEach(modal => {
      const modalScrollArea = modal.querySelector('.modal-middle.scroll');
      const modalScrollBox = modal.querySelector('.modal-scroll-box');

      if(modalScrollBox){
        modalScrollBox.addEventListener('scroll', function(){
          const scrollHeight = modalScrollBox.scrollHeight;
          const scrollPosition = modalScrollBox.clientHeight + modalScrollBox.scrollTop;

          if (scrollPosition >= scrollHeight) {
            modalScrollArea.classList.add('reach');
          } else {
            modalScrollArea.classList.remove('reach');
          }
        })
      }
    })
  }
});

// dialog modal
function isIOS() {
  return /iPad|iPhone|iPod/.test(navigator.userAgent) && !window.MSStream;
}

function enableScrollLock(wrapperY) {
  const { body } = document;
  const wrapper = document.querySelector('.wrapper');

  if (!body.getAttribute('scrollY')) {
    body.style.overflow = 'hidden';

    if (isIOS()) {
      body.setAttribute('scrollY', wrapperY.toString());
      Object.assign(body.style, {
        position: 'fixed',
        left: '0',
        right: '0',
        bottom: '0',
        top: `-${wrapperY}px`,
      });
      wrapper.style.overflow = 'visible';
    }
  }
}

function disableScrollLock() {
  const { body } = document;
  const wrapper = document.querySelector('.wrapper');

  if (body.getAttribute('scrollY')) {
    body.style.removeProperty('overflow');

    if (isIOS()) {
      Object.assign(body.style, {
        position: '',
        top: '',
        left: '',
        right: '',
        bottom: '',
      });
      wrapper.style.removeProperty('overflow');
      window.scrollTo(0, Number(body.getAttribute('scrollY')));
      body.removeAttribute('scrollY');
    }
  }
}

function initModals() {
  const dialogs = document.querySelectorAll('.modal');
  const btnShows = document.querySelectorAll('.btn-modal');
  let wrapperY = 0;

  const wrapper = document.querySelector('.wrapper');
  if (!wrapper) {
    console.error('Element with class "wrapper" not found.');
    return;
  }

  wrapperY = wrapper.scrollTop;

  wrapper.addEventListener('scroll', () => {
    wrapperY = wrapper.scrollTop;
  });

  dialogs.forEach((dialog) => {
    const btnClose = dialog.querySelector('.btn-close');

    btnShows.forEach((btnShow) => {
      btnShow.addEventListener('click', () => {
        const modalId = btnShow.getAttribute('data-modal');
        if (modalId) {
          const modalEl = document.getElementById(modalId);
          modalEl.classList.add('active');
          enableScrollLock(wrapperY);
        }
      });
    });

    btnClose.addEventListener('click', () => {
      dialog.classList.remove('active');
      disableScrollLock();
    });

    dialog.addEventListener('click', (e) => {
      if (e.target === dialog) {
        dialog.classList.remove('active');
        disableScrollLock();
      }
    });
  });
}

document.addEventListener('DOMContentLoaded', initModals);

